package com.teamset.colcap.domain.identifier.generator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

/**
 * Example usage
 * As annotation on top of the field
 * @GenericGenerator(
 *			name="life_policy_id,
 *			strategy="com.rtim.domain.identifier.generator.SerialnumberGenerator",
 *			parameters={
 *					@Parameter(name=SerialnumberGenerator.TYPE_NAME, value="proposal"),
 *					@Parameter(name=SerialnumberGenerator.PREFIX, value="PRO"),
 *			})
 * @GeneratedValue(generator="life_policy_id")
 * String id;
 */
public class SerialnumberGenerator
implements IdentifierGenerator, Configurable {
	public static final String TYPE_NAME = "typeName";
	
	// We only use prefix + date + serial number
	public static final String PREFIX = "prefix";
	public static final String DATE_PATTERN = "datePattern";
	public static final String NUMBER_PATTERN = "numberPattern";
	
	
	public static final String DEFAULT_DATE_PATTERN = "yyMMdd";
	public static final String DEFAULT_NUMBER_PATTERN = "0000";
	// Initial count
	private static final int START = 1;
	
	// Lock
	private static Object lock = new Object();
	
	// Instant variable
	private String typeName;
	private String prefix;
	private String datePattern;
	private String numberPattern;
	private DateFormat dateFormat;
	private DecimalFormat numberFormat;
	
	@Override
	public void configure(Type type, Properties params, Dialect d)
	throws MappingException {
		// Type name
		this.typeName = params.getProperty(TYPE_NAME);
		if (this.typeName == null) {
			throw new MappingException("No type is defined.");
		}
		
		// We accept no prefix
		this.prefix = params.getProperty(PREFIX);
		if (this.prefix == null) {
			this.prefix = "";
		}
		
		//use default pattern if no pattern is specified
		this.datePattern = params.getProperty(DATE_PATTERN);
		if (this.datePattern == null) {
			this.datePattern = DEFAULT_DATE_PATTERN;
		}
		
		//use default pattern if no pattern is specified
		this.numberPattern = params.getProperty(NUMBER_PATTERN);
		if (this.numberPattern == null) {
			this.numberPattern = DEFAULT_NUMBER_PATTERN;
		}
		// Pre-defined pattern
		try {
			this.numberFormat = new DecimalFormat(this.numberPattern);
			this.dateFormat = new SimpleDateFormat(this.datePattern);
		} catch (Exception ex) {
			throw new MappingException(ex);
		}
	}
	
	@Override
	public Serializable generate(SessionImplementor session, Object object)
	throws HibernateException {
		synchronized (lock) {
			try {
				// Create db access
				SerialNumberDBAccess db = new SerialNumberDBAccess(session.connection(), typeName);
				
				// Get current serial number
				String currSN = db.getCurrSerialNumber();
				Date currDate = new Date();
				int nextValue;
				String nextSN;
				if (currSN == null || currSN.equals("")) {
					// No existing serial number
					nextValue = START;
					nextSN = generateSN(currDate, nextValue);
					db.save(nextSN);
				} else {
					// Analyse the current serial number
					String currDateStr = dateFormat.format(currDate);
					int datePos = prefix.length(); // Serial number starts with prefix, then followed by date, position of date is length of prefix
					int numPos = datePos + currDateStr.length(); // Number comes after date
					String lastDateStr = currSN.substring(datePos, numPos);	
					if(currDateStr.equals(lastDateStr)){
						// Same date, increment the current serial number
						nextValue = Integer.parseInt(currSN.substring(numPos)) + 1;
					} else {
						// Different date, reset counter
						nextValue = START;
					}
					nextSN = generateSN(currDate, nextValue);
					db.update(nextSN);
				}		

				db.commit();
				return nextSN;
	
			} catch (Exception ex) {
				throw new HibernateException("Unable to generate ID", ex);
			}
		}
	}
	
	private String generateSN(Date date, int value) {
		return prefix + dateFormat.format(date) + numberFormat.format(value);
	}
}

package com.teamset.colcap.domain.identifier.generator;

import java.io.Serializable;
import java.text.DecimalFormat;
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
 *			name="seq_id",
 *			strategy="com.rtim.domain.identifier.generator.PrefixedNumberGenerator",
 *			parameters={
 *					@Parameter(name=PrefixedNumberGenerator.TYPE_NAME, value="clientAccount"),
 *					@Parameter(name=PrefixedNumberGenerator.PATTERN, value="CA00000000")
 *			})
 * @GeneratedValue(generator="seq_id")
 * String id;
 * 
 * A few note of the pattern
 *  - Can accept prefix or suffix
 *  - Not support number separator at the moment, though spaces are ok
 *  - Refering the format at http://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
 */
public class PrefixedNumberGenerator
implements IdentifierGenerator, Configurable {
	public static final String TYPE_NAME = "typeName";
	public static final String PATTERN = "pattern";
	private static final int START = 1;
	
	private static Object lock = new Object();
	
	private String typeName;
	private DecimalFormat pattern;
	
	@Override
	public void configure(Type type, Properties params, Dialect d)
	throws MappingException {
		// Sequence name
		this.typeName = params.getProperty(TYPE_NAME);
		if (this.typeName == null) {
			throw new MappingException("No type name is defined.");
		}
		
		// Pattern
		try {
			this.pattern = new DecimalFormat(params.getProperty(PATTERN));
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
				int nextValue;
				String nextSN;
				if (currSN == null || currSN.equals("")) {
					// No existing serial number
					nextValue = START;
					nextSN = pattern.format(nextValue);
					db.save(nextSN);
					
				} else {
					// Increment the current serial number
					nextValue = pattern.parse(currSN).intValue() + 1;
					nextSN = pattern.format(nextValue);
					db.update(nextSN);
				}
				
				db.commit();
				return nextSN;
	
			} catch (Exception ex) {
				throw new HibernateException("Unable to generate ID", ex);
			}
		}
	}
}

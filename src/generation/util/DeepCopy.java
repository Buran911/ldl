package generation.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import application.util.Halt;
import application.util.StackTrace;

/**
 *  ласс осуществл€ет копирование объекта использу€ сереализацию.
 * ’ак, не использовать.
 * @author hindu
 * */
public class DeepCopy {
    private static Logger logger = Logger.getLogger(DeepCopy.class);
    
    public static Object getCopy(Object orig) {
	Object obj = null;
	try {
	    // Write the object out to a byte array
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream out = new ObjectOutputStream(bos);
	    out.writeObject(orig);
	    out.flush();
	    out.close();

	    // Make an input stream from the byte array and read
	    // a copy of the object back in
	    ObjectInputStream in = new ObjectInputStream(
		    new ByteArrayInputStream(bos.toByteArray()));
	    obj = in.readObject();
	} catch (IOException e) {
	    logger.error("ќшибка ввода/вывода при копировании объекта через DeepCopy.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	} catch (ClassNotFoundException cnfe) {
	    logger.error("ќшибка при копировании объекта через DeepCopy.");
	    logger.trace( StackTrace.getStackTrace(cnfe));
	    throw new Halt();
	}
	return obj;

    }
}

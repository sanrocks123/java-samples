
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.file.handlers;

import java.io.StringWriter;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sanjeev.logging.entities.Message;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class XmlBasedFileHandler extends AbstractFileHandler {

    /**
     * @param prop
     */
    public XmlBasedFileHandler(Properties prop) {
        super(prop);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sanjeev.logging.file.handlers.AbstractFileHandler#writeToLogFile(java
     * .io.PrintWriter, com.sanjeev.logging.entities.Message)
     */
    @Override
    protected String formatMessage(Message msg) {
        String formattedMsg = "";
        try {
            JAXBContext jaxContext = JAXBContext.newInstance(Message.class);
            Marshaller marshaller = jaxContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            msg.setMessage(msg.getMessage().toString());
            StringWriter sw = new StringWriter();
            marshaller.marshal(msg, sw);
            formattedMsg = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return formattedMsg;
    }

}

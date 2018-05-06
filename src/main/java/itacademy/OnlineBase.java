package itacademy;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class OnlineBase {
    private Set<Patient> listOnline = new LinkedHashSet<>();

    public Set<Patient> getOnlineInfo() {
        try {
            PatientHandler patientHandler = new PatientHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(patientHandler);
            URL url=new URL("https://raw.githubusercontent.com/Zaitsev-95/xml/master/BaseXml_10.xml");
            reader.parse(new InputSource(url.openStream()));
            listOnline = patientHandler.getListOfPatient();
        } catch (IOException |SAXException e) {
            e.printStackTrace();
        }
        return listOnline;
    }
}
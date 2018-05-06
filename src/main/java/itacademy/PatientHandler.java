package itacademy;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class PatientHandler extends DefaultHandler {
    private HashSet<Patient> listOfPatient = new HashSet<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private String name = null;
    private String secondName = null;
    private String birthday = null;
    private String stateOfHealth = null;
    private boolean n = false;
    private boolean s = false;
    private boolean b = false;
    private boolean st = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {
            n = true;
        } else if (qName.equalsIgnoreCase("secondName")) {
            s = true;
        } else if (qName.equalsIgnoreCase("birthday")) {
            b = true;
        } else if (qName.equalsIgnoreCase("stateOfHealth")) {
            st = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length)  {
        if (n) {
            name = new String(ch, start, length);
            n = false;
        } else if (s) {
            secondName = new String(ch, start, length);
            s = false;
        } else if (b) {
            birthday = new String(ch, start, length);
            b = false;
        } else if (st) {
            stateOfHealth = new String(ch, start, length);
            st = false;
        }
    }

    public HashSet<Patient> getListOfPatient() {
        return listOfPatient;
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName)  {
        if (qName.equalsIgnoreCase("patient")) {
            listOfPatient.add(new Patient(name, secondName, convertDate(birthday), Boolean.valueOf(stateOfHealth)));
        }
    }

    private Date convertDate(String birthday) {
        try {
            Date date;
            return date = sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
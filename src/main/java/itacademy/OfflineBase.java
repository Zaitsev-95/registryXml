package itacademy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class OfflineBase {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public void writeInfo(Set<Patient> listPatients) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hospital = document.createElement("hospital");
            document.appendChild(hospital);
            for (Patient addPatient : listPatients) {
                Element patient = document.createElement("addPatient");
                Element name = document.createElement("name");
                Element secondName = document.createElement("secondName");
                Element birthday = document.createElement("birthday");
                Element healthy = document.createElement("healthy");
                name.setTextContent(addPatient.getName());
                secondName.setTextContent(addPatient.getSecondName());
                birthday.setTextContent(simpleDateFormat.format(addPatient.getBirthday())); // дата в формате
                healthy.setTextContent(String.valueOf(addPatient.isHealthy()));
                hospital.appendChild(patient);
                patient.appendChild(name);
                patient.appendChild(secondName);
                patient.appendChild(birthday);
                patient.appendChild(healthy);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("base.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Set<Patient> listPatients = new LinkedHashSet<>();

    public Set<Patient> readInfo() {
        String name = null, secondName = null, birthday = null, healthy = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("base.xml");
            Node root = document.getDocumentElement();
            NodeList patients = root.getChildNodes();
            for (int i = 0; i < patients.getLength(); i++) {
                Node patient = patients.item(i);
                if (patient.getNodeType() != Node.TEXT_NODE) {
                    NodeList patientProps = patient.getChildNodes();
                    for (int j = 0; j < patientProps.getLength(); j++) {
                        Node patientProp = patientProps.item(j);
                        if (patientProp.getNodeType() != Node.TEXT_NODE) {
                            if (patientProp.getNodeName().equals("name"))
                                name = patientProp.getChildNodes().item(0).getTextContent();
                            else if (patientProp.getNodeName().equals("secondName"))
                                secondName = patientProp.getChildNodes().item(0).getTextContent();
                            else if (patientProp.getNodeName().equals("birthday"))
                                birthday = patientProp.getChildNodes().item(0).getTextContent();
                            else if (patientProp.getNodeName().equals("healthy"))
                                healthy = patientProp.getChildNodes().item(0).getTextContent();
                        }
                    }
                    listPatients.add(new Patient(name, secondName, convertDate(birthday), Boolean.valueOf(healthy)));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listPatients;
    }

    private Date convertDate(String birthday) {
        try {
            Date date;
            return date = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
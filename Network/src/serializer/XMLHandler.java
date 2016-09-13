package serializer;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import Game.GameState;

public class XMLHandler {
	public static String serialize(GameState gs) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GameState.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(gs, sw);
			System.out.println(sw.toString());
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static GameState deserialize(String xml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GameState.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			GameState gs = (GameState) jaxbUnmarshaller.unmarshal(reader);
			return gs;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}

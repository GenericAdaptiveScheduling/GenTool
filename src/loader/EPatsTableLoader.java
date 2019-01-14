package loader;

import java.io.File;
import java.util.List;

import kernel.Context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import ui.editor.EPatsTable;

public class EPatsTableLoader {
	public static EPatsTable getPatsTable(String fileName)
	{
		EPatsTable patTable = new EPatsTable();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(fileName));
			Element patternsElement = document.getRootElement();
			List<Element> list = patternsElement.elements();
			int count = list.size();
			for(int i = 0;i != count;++i)
			{
				Element patternElement = (Element) list.get(i);
				Element idElement = (Element) patternElement.elements().get(0);
				Element additionElement = (Element) patternElement.elements().get(1);
				Element deletionElement = (Element) patternElement.elements().get(2);
				Element updateElement = (Element) patternElement.elements().get(3);
				
				String patName = idElement.getTextTrim();
				String additionString = additionElement.getTextTrim();
				String deletionString = deletionElement.getTextTrim();
				String updateString = updateElement.getTextTrim();
				Context context = new Context();
				context.setName(patName);
				context.setOp(Boolean.parseBoolean(additionString), Boolean.parseBoolean(deletionString), Boolean.parseBoolean(updateString));
				patTable.addPattern(context);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return patTable;
	}
}

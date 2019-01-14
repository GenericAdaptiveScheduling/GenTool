package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import kernel.Context;
import kernel.Pair;
import kernel.Rule;
import manager.FilesPathManager;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import formula.*;
import ui.editor.ERulesTable;


public class ERulesTableLoader {
	public static ERulesTable getRulesTable(Vector<Context> patterns,String ruleFileName) {
		ERulesTable eRulesTable = new ERulesTable();
		try 
		{
			SAXReader reader = new SAXReader();
			
			Document document = reader.read(new File(ruleFileName));
			Element rulesElement = document.getRootElement();
			for(Element ruleElement: rulesElement.elements())
			{
				Element idElement = ruleElement.elements().get(0);
				String ruleName = idElement.getTextTrim();
				//System.out.println("Loading Rule:" + ruleName + " from: " + ruleFileName);
				Element formulaElement = ruleElement.elements().get(1);
				Element rootFmlElm = formulaElement.elements().get(0);
				Rule newRule = new Rule();
				newRule.setName(ruleName);
				newRule.setFormula(parseFmlElm(patterns,rootFmlElm));
				eRulesTable.addEditorRule(ruleName,newRule);
			}
		} 
		catch (DocumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eRulesTable;
	}
	
	public static Rule getRule(String ruleName, Vector<Rule> rules) {
		for(int i = 0;i < rules.size();i++) {
			if(rules.get(i).getRuleName().equals(ruleName))
				return rules.get(i);
		}
		return null;
	}
	
	public static void getAndSave(Vector<Rule> rules) throws FileNotFoundException {
		String fileName = FilesPathManager.getRulesFileName();
		String saveFileName = FilesPathManager.getSaveRulesFileName();
		Document document = null;
		try 
		{
			SAXReader reader = new SAXReader();
			document = reader.read(new File(fileName));			
			Element rulesElement = document.getRootElement();
			for(Element ruleElement: rulesElement.elements())
			{
				Element patterns = ruleElement.addElement("patterns");
				Element idElement = ruleElement.elements().get(0);
				String ruleName = idElement.getTextTrim();
				Vector<Pair> pairs = getRule(ruleName,rules).getPairs();
				for(int i = 0;i < pairs.size();i++) {
					Pair pair = pairs.get(i);
					Element pattern = patterns.addElement("pattern");
					pattern.addElement("first").addAttribute("type",pair.getfType())
						.addAttribute("name",pair.getfName());
					pattern.addElement("second").addAttribute("type",pair.getsType())
						.addAttribute("name",pair.getsName());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
        	//设置XML文档格式
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();
			//设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
			outputFormat.setEncoding("UTF-8");
			outputFormat.setNewlines(true);//设置换行
			outputFormat.setIndent(true); //设置是否缩进
			outputFormat.setIndent("    "); //以四个空格方式实现缩进
			
            XMLWriter writer = new XMLWriter(outputFormat);
            FileOutputStream fos = new FileOutputStream(saveFileName);
            writer.setOutputStream(fos);
            writer.write(document);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static Context getContextById(Vector<Context> patterns,String ctx) {
		Context result = null;
        for (Context context : patterns) {
            if (context.getContextname().equals(ctx)) {
                result = context;
                break;
            }
        }
        return result;
	}
	
	public static Formula parseFmlElm(Vector<Context> patterns,Element fmlElm)
	{
		String elementText = fmlElm.getName();
		if(elementText.equals(new String("forall")))
		{
			String var = fmlElm.attributeValue("var");
			String context = fmlElm.attributeValue("in");
			ForallFormula resultFormula = new ForallFormula("forall");
			resultFormula.setContext(var, getContextById(patterns,context));
			Element chlElement = fmlElm.elements().get(0);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlElement));
			return resultFormula;
		}
		else if (elementText.equals(new String("exists"))) {
			String var = fmlElm.attributeValue("var");
			String context = fmlElm.attributeValue("in");
			ExistsFormula resultFormula = new ExistsFormula("exists");
			resultFormula.setContext(var, getContextById(patterns,context));
			Element chlElement = fmlElm.elements().get(0);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlElement));
			return resultFormula;
		}
		else if(elementText.equals(new String("and")))
		{
			AndFormula resultFormula = new AndFormula("and");
			Element chlOneElm = fmlElm.elements().get(0);
			Element chlTwoElm = fmlElm.elements().get(1);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlOneElm), parseFmlElm(patterns,chlTwoElm));
			return resultFormula;
		}
		else if (elementText.equals(new String("or"))) {
			OrFormula resultFormula = new OrFormula("or");
			Element chlOneElm = fmlElm.elements().get(0);
			Element chlTwoElm = fmlElm.elements().get(1);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlOneElm), parseFmlElm(patterns,chlTwoElm));
			return resultFormula;
		}
		else if (elementText.equals(new String("implies"))) {
			ImpliesFormula resultFormula = new ImpliesFormula("implies");
			Element chlOneElm = fmlElm.elements().get(0);
			Element chlTwoElm = fmlElm.elements().get(1);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlOneElm), parseFmlElm(patterns,chlTwoElm));
			return resultFormula;
		}
		else if (elementText.equals(new String("not"))) {
			UnaryFormula resultFormula = new UnaryFormula("not");
			Element chlElement = fmlElm.elements().get(0);
			resultFormula.setSubFormula(parseFmlElm(patterns,chlElement));
			return resultFormula;
		}
		else if (elementText.equals(new String("bfunc"))) {
			String bFuncName = fmlElm.attributeValue("name");
			BFunc resultFormula = new BFunc(bFuncName);
			for(Element chlElm: fmlElm.elements())
			{
				String var = chlElm.attributeValue("var");
				String field = chlElm.attributeValue("field");
				String position = chlElm.attributeValue("pos");
				resultFormula.setParam(position, var, field);
			}
			return resultFormula;
		}
		
		return null;
	}
}

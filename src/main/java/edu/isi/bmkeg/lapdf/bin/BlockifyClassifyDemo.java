	package edu.isi.bmkeg.lapdf.bin;

import java.io.File;

import edu.isi.bmkeg.lapdf.controller.LapdfEngineEx;
import edu.isi.bmkeg.lapdf.model.LapdfDocument;
import edu.isi.bmkeg.lapdf.xml.model.LapdftextXMLDocument;
import edu.isi.bmkeg.utils.xml.XmlBindingTools;

/**
 * This class for testing Drools rule to detect parts of an article
 * @author Phat Nguyen
 *
 */

public class BlockifyClassifyDemo {

	// Start page of pdf need to be extracted
	public static final int START = 70;
	
	// End page of pdf need to be extracted
	public static final int END = 70;
	
	public static final String ARTICLE_FILE = "sph_article_title_rules.drl";
	
	public static final String HIGHLIGHT_FILE = "sph_article_highlight_rules.drl";
	
	public static final String BODY_FILE = "sph_article_body_rules.drl";
	
	public static final String RULES_FILE = "sph_article_rules.drl";
	
	// Pdf file input
	public static final String INPUT_FILE = "/Users/phat/Desktop/Article-Pdf/1603_AS_HK.pdf";
	
	// Directory output
	public static final String OUTPUT_DIR = "/Users/phat/Desktop/Article-Pdf/result/demo3";
	
	// Drools rules file
	public static final String RULE_FILE = "/Users/phat/Development/source-code/pdf-parser/src/main/resources/rules/" + BODY_FILE;
	
	public static void main(String[] args) throws Exception {
		
		File inputFileOrDir = new File(INPUT_FILE);
		
		File outDir = new File(OUTPUT_DIR);
		
		File ruleFile = new File(RULE_FILE);
		
		// Init engine
		LapdfEngineEx engine = new LapdfEngineEx();
		engine.setStartPage(START);
		engine.setEndPage(END);
		
		String pdfStem = inputFileOrDir.getName();
		pdfStem = pdfStem.replaceAll("\\.pdf", "");
		
		String outPath = outDir + "/" + pdfStem + "_" + START + "-" + END + "_lapdf_" + System.currentTimeMillis() + ".xml";
		
		File outXmlFile = new File(outPath);
		
		LapdfDocument lapdf = engine.blockifyFile(inputFileOrDir);
		
		engine.classifyDocument(lapdf, ruleFile);
		
		LapdftextXMLDocument xmlDoc = lapdf.convertToLapdftextXmlFormat(START, END);
		
		XmlBindingTools.saveAsXml(xmlDoc, outXmlFile);
	}
}

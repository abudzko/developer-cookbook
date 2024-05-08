package com.budzko.cookbook.java.xml;

import lombok.SneakyThrows;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XmlParser {
    @SneakyThrows
    public static void main(String[] args) {
        new XmlParser().parse();
    }

    private void parse() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(getClass().getClassLoader().getResourceAsStream("./xml/body.xml"));
        var root = document.getRootElement();
        var id = root.selectSingleNode("//content/user/doc/id");
        System.out.println(root.elements().get(0).getName());
        System.out.println(id.getText());
    }
}

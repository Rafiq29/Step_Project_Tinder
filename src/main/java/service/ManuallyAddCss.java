package service;

import libs.TemplateEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ManuallyAddCss {
    public HashMap<String, Object> addCss(boolean addBoot,boolean addStyle,  boolean addFont) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        if(addBoot)
        {
            BufferedReader bootstrapReader = new BufferedReader(new FileReader(new File("./content/css/bootstrap.min.css")));
            String bootstrap = "<style>" + bootstrapReader.lines().collect(Collectors.joining()) + "</style>";
            data.put("boot",bootstrap);
        }
        if (addFont)
        {
            BufferedReader fontReader = new BufferedReader(new FileReader(new File("./content/css/font.css")));
            String font = "<style>" + fontReader.lines().collect(Collectors.joining()) + "</style>";
            data.put("font",font);
        }
        if(addStyle)
        {
            BufferedReader styleReader = new BufferedReader(new FileReader(new File("./content/css/style.css")));
            String style = "<style>" + styleReader.lines().collect(Collectors.joining()) + "</style>";
            data.put("style",style);
        }
        return data;
    }
}

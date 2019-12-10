package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ManuallyAddCss {
    private HashMap<String, Object> data;

    public ManuallyAddCss() {
        data = new HashMap<>();
    }

    public void addCssBoot() throws FileNotFoundException {
        BufferedReader bootstrapReader = new BufferedReader(new FileReader(new File("./content/css/bootstrap.min.css")));
        String bootstrap = "<style>" + bootstrapReader.lines().collect(Collectors.joining()) + "</style>";
        data.put("boot", bootstrap);
    }

    public void addCssFont() throws FileNotFoundException {
        BufferedReader fontReader = new BufferedReader(new FileReader(new File("./content/css/font.css")));
        String font = "<style>" + fontReader.lines().collect(Collectors.joining()) + "</style>";
        data.put("font", font);
    }

    public void addCssStyle() throws FileNotFoundException {
        BufferedReader styleReader = new BufferedReader(new FileReader(new File("./content/css/style.css")));
        String style = "<style>" + styleReader.lines().collect(Collectors.joining()) + "</style>";
        data.put("style", style);
    }

    public HashMap<String, Object> get() {
        return data;
    }
}

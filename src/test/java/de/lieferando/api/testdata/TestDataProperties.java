package de.lieferando.api.testdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestDataProperties implements TestData {

    private final Properties texts;

    public TestDataProperties() {
        // can understand the environment and test contexts somehow from system properties, etc
        // Here just sample dummy implementation
        this.texts = new Properties();
        try {
            texts.load(Files.newBufferedReader(Paths.get("farm_texts.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // For now just simple implementation
    @Override
    public String happyChickenMessage() {
        return texts.getProperty("chicken.emotional.status.happy");
    }

    @Override
    public String chickenIsFullMessage() {
        return texts.getProperty("error.generic");
    }

    @Override
    public String collectedEggsMessage(int eggsNumber) {
        return null;
    }

    @Override
    public String failToCollectEggsMessage() {
        return null;
    }

    @Override
    public String dailyCollectedEggsMessage(int eggsNumber) {
        return null;
    }

}

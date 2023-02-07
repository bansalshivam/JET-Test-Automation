package de.lieferando.api.testdata;

public interface TestData {

    String happyChickenMessage();

    String chickenIsFullMessage();

    String collectedEggsMessage(int eggsNumber);

    String failToCollectEggsMessage();

    String dailyCollectedEggsMessage(int eggsNumber);

}

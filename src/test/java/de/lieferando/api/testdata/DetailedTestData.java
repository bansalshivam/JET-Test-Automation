package de.lieferando.api.testdata;

public class DetailedTestData implements TestData {

    @Override
    public String happyChickenMessage() {
        return "Your chickens are now full and happy";
    }

    @Override
    public String chickenIsFullMessage() {
        return "You just fed them! Do you want them to explode??";
    }

    @Override
    public String collectedEggsMessage(int eggsNumber) {
        return String.format("Hey look at that, %s eggs have been collected!", eggsNumber);
    }

    @Override
    public String failToCollectEggsMessage() {
        return "Hey, give the ladies a break. Makin' eggs ain't easy!";
    }

    @Override
    public String dailyCollectedEggsMessage(int eggsNumber) {
        return String.format("You have collected a total of %s eggs today", eggsNumber);
    }
}

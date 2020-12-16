package my.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This class is for telegram bot calculate length of Strings
 * to calculate how many alphabets accourence repeatedly
 *
 * @author Nasuha
 */
public class nasuhaBot extends TelegramLongPollingBot {

    //to declare limit char that can input by user
    static final int MAX_CHAR = 300;

    /**
     * This method is to getting update from user.
     *
     * @param update get update message from telegram bot
     */
    @Override
    public void onUpdateReceived(Update update) {

        //this method is to get message in telegram
        String command = update.getMessage().getText();

        //this method is to get user name for their telegram
        String name = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
        SendMessage message1 = new SendMessage();
        message1.setText("From User : " + name + "\nMessage : " + command);

        //this method to get length of the strings input by user
        int length = command.length();
        SendMessage message2 = new SendMessage();
        message2.setText("\nThe length of the string is : " + length);

        //to calculate letter that occur than 3 times
        int[] count = new int[MAX_CHAR];
        int character = command.length();
        SendMessage message3 = new SendMessage();

        for (int i = 0; i < character; i++)
            count[command.charAt(i)]++;

        char[] ch = new char[command.length()];

        for (int i = 0; i < character; i++) {
            ch[i] = command.charAt(i);
            int find = 0;

            for (int j = 0; j <= i; j++) {
                if (command.charAt(i) == ch[j])
                    find++;
            }

            //to execute the letter that occur more 3 times
            if (Character.isLetter(command.charAt(i))) {
                if (find == 1) {
                    //Only letter in a string that occur more than 3 times
                    if (count[command.charAt(i)] > 3) {
                        message3.setText("Letter in a string that occur more than 3 times are : \nLetter '" + command.charAt(i) + "' \n it occur : " + count[command.charAt(i)] + " Times");
                    }
                }
            }
        }

        //method to send back the message to user in telegram bot
        message1.setChatId(update.getMessage().getChatId());
        message2.setChatId(update.getMessage().getChatId());
        message3.setChatId(update.getMessage().getChatId());
        try {
            execute(message1);
            execute(message2);
            execute(message3);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    //the name of the telegram bot
    @Override
    public String getBotUsername() {
        return "s259265_bot";
    }

    //token of the telegram bot
    @Override
    public String getBotToken() {
        return "1224939396:AAFrNfUb6AMyMHdJ7iQxGmpDZKHXsdUAIYs";
    }
}
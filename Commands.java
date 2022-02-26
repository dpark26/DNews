import java.awt.Color;
import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        String[] msgs = e.getMessage().getContentRaw().split(" ");

        if (msgs[0].length() > 1) {
            if (msgs[0].equalsIgnoreCase(DNews.pre + "help")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("DNews");
                embed.setDescription("Discord bot for scraping headlines from BBC News. All news information from me are provided by BBC News.");
                embed.addField("Commands", "#news : displays the section topics from the BBC website\n#news {topic #} : gets a link to recent headlines for that section", true);
                embed.setColor(new Color(207, 250, 255));
                embed.setFooter("Created by Daniel Park");
                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessageEmbeds(embed.build()).queue();
                embed.clear();
            }

            else if (msgs[0].equalsIgnoreCase(DNews.pre + "news") && msgs.length == 1) {
                String msg = "";
                try {
                    Scraper s = new Scraper();
                    msg = "Here are the main section topics scraped from the BBC website:\n";
                    int index = 1;
                    for (String str : s.getMainTopics()) {
                        msg += (index++) + ". " + str + "\n";
                    }
                    msg += "Type #news {section number} to get the latest news!";
                } catch (IOException e1) {
                    msg = "Rip";
                }

                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessage(msg).queue();
            }

            else if (msgs[0].equalsIgnoreCase(DNews.pre + "news") && msgs.length > 1) {
                String msg = "";
                try {
                    Scraper s = new Scraper();
                    int index = Integer.parseInt(msgs[1]) - 1;
                    msg = "Here are the recent headlines for " + s.getMainTopics()[index] + ":\n";
                    msg += "https://www.bbc.com" + s.getUrls()[index] + "\n";
                } catch (IOException e1) {
                    msg = "Rip";
                } catch (Exception e2) {
                    msg = "Sorry, but that was an invalid input.";
                }

                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessage(msg).queue();
            }
    
            else if (msgs[0].equalsIgnoreCase("ping")) {
                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessage("pong").queue();
            }
    
            else if (msgs[0].charAt(0) == '#'){
                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessage("Sorry, idk that commmand!").queue();
                e.getChannel().sendTyping().queue();
                e.getChannel().sendMessage("Type #help for a list of commands.").queue();
            }
        }
    }
}

package ml.dyropedia.GameOfLife;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ml.dyropedia.GameOfLife.GameOfLife;
import org.bukkit.util.ChatPaginator;

import java.util.Locale;

public class CommandGol implements CommandExecutor {
    GameOfLife mainInstance;
    public CommandGol(GameOfLife gmainInstance){
        mainInstance=gmainInstance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            sender.sendMessage(((Player)sender).getDisplayName()+" is good, yes?");
            if(!(args.length<1)) {
                try {
                    int size = Integer.parseInt(args[0]);

                    try{
                        mainInstance.cellMat[0]=Material.valueOf(args[1].toUpperCase());
                    }
                    catch(IllegalArgumentException e){
                        sender.sendMessage("Unknown Material");
                        return false;
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    try{
                        mainInstance.cellMat[1]=Material.valueOf(args[2].toUpperCase());
                    }
                    catch(IllegalArgumentException e){
                        sender.sendMessage("Unknown Material");
                        return false;
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }

                    if (size < 201 && size > 0) {
                        //GameOfLife main=GameOfLife.getMainInstance();
                        mainInstance.createBoard(size,((Player)sender));
                    }
                    else{return false;}
                }
                catch(NumberFormatException e){
                    return false;
                }
            }
            return true;
        }
        else if(sender instanceof ConsoleCommandSender){
            System.out.println("Plugin is good, yes?");
            return true;
        }
        return false;
    }
}

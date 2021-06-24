package ml.dyropedia.GameOfLife;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandGolstart extends BukkitRunnable implements CommandExecutor {
    //public GameOfLife mainInstance=GameOfLife.getMainInstance();
    public GameOfLife mainInstance;
    public CommandGolstart(GameOfLife gmainInstance){
        mainInstance=gmainInstance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //mainInstance=GameOfLife.getMainInstance();

        /*if (mainInstance.running) {
            mainInstance.running=false;
            return true;
        }*/
        if(!(sender instanceof Player)){
            System.out.println("A player must start the game of life");
            return true;
        }

        if(mainInstance.boardArray!=null){
            //mainInstance.running=true;
            int speed=10;
            try{
                speed=Integer.parseInt(args[0]);
            }
            catch(ArrayIndexOutOfBoundsException e){

            }
            catch(NumberFormatException e){
                sender.sendMessage("That's not a number");
            }
            mainInstance.startGame((Player) sender,speed);
            return true;
        }
        else{sender.sendMessage("Board not initialized"); }
        return false;
    }

    @Override
    public void run(){
        mainInstance.gameLoop();
    }

}

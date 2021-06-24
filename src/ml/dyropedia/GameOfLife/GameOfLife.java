package ml.dyropedia.GameOfLife;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class GameOfLife extends JavaPlugin {
    public Block[][] boardArray;
    public boolean[][] cellLife;
    public Material cellMat[]={Material.BLACK_WOOL,Material.WHITE_WOOL};
    private GameOfLife MainInstance;
    public boolean running=false;
    public BukkitTask timer;

    @Override public void onEnable(){
        this.getCommand("gol").setExecutor(new CommandGol(this));
        this.getCommand("golstart").setExecutor(new CommandGolstart(this));
        MainInstance=this;

    }
    @Override public void onDisable(){

    }


    public void createBoard(int setSize, Player sender){
        sender.sendMessage("Set board size to " + setSize);
        Block pos=((Player) sender).getLocation().getBlock();
        boardArray=new Block[setSize][setSize];

        for(int i=0;i<setSize;i++){
            pos=pos.getRelative(BlockFace.NORTH);
            Block temppos=pos;
            for(int y=0;y<setSize;y++){
                boardArray[i][y]=temppos.getRelative(BlockFace.EAST);
                temppos=temppos.getRelative(BlockFace.EAST);
                boardArray[i][y].setType(cellMat[0]);
            }

        }



        //pos.setType(cellMat[0]);
        //int board[][]=new int[setSize][setSize];
    }

    public void startGame(Player sender, int speed){
        if(!running) {
            timer = new CommandGolstart(this).runTaskTimer(this, 20, speed);
            running=true;
            sender.sendMessage("Started Game of Life on the board");
        }
        else{
            timer.cancel();
            running=false;
            sender.sendMessage("Stopped the Game of Life");
        }
    }

    public void gameLoop(){
        BlockFace dir[] ={BlockFace.EAST,BlockFace.NORTH,BlockFace.SOUTH,BlockFace.WEST,BlockFace.NORTH_EAST,BlockFace.NORTH_WEST,BlockFace.SOUTH_EAST,BlockFace.SOUTH_WEST};
        cellLife=new boolean[boardArray.length][boardArray[0].length];

        //System.out.println("Running");
        for(int i=0;i<boardArray.length;i++){
            for(int y=0;y<boardArray[i].length;y++){
                int count=0;
                for(int a=0;a< dir.length;a++) {
                    if (boardArray[i][y].getRelative(dir[a]).getType() == cellMat[1]) {
                        count++;
                    }
                }
                    if((count==2||count==3)&&boardArray[i][y].getType()==cellMat[1]){
                        //boardArray[i][y].setType(cellMat[0]);
                        cellLife[i][y]=true;
                    }
                    else if((boardArray[i][y].getType()==cellMat[0])&&count==3){
                        //boardArray[i][y].setType(cellMat[0]);
                        cellLife[i][y]=true;
                    }
                    else{
                        cellLife[i][y]=false;
                    }
            }
        }
        for(int i=0;i< cellLife.length;i++){
            for(int y=0;y< cellLife[i].length;y++){
                if(cellLife[i][y]) {
                    boardArray[i][y].setType(cellMat[1]);
                }
                else{
                    boardArray[i][y].setType(cellMat[0]);
                }
            }
        }
    }

    public GameOfLife getMainInstance(){
        return MainInstance;
    }
}

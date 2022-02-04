package gameSystem;

public class RunImpl implements Run{

    private final boolean win;
    private final int enemyKilled;
    private final Level level;
    private final boolean isEpic;
    private  final int id;



    public RunImpl(boolean win, int enemyKilled, Level level, boolean isEpic, int id){
        this.win = win;
        this.enemyKilled = enemyKilled;
        this.isEpic = isEpic;
        this.level = level;
        this.id = id;
    }

    @Override
    public double getScore() {
        double basicScore;
        if (win){
            basicScore = 10 + 2 * enemyKilled;
        }
        else{
            basicScore = -10 + 2 * enemyKilled;
        }

        if (level == Level.HARD){
            basicScore = 1.5 * basicScore;
        }
        return isEpic ? (1.5 * basicScore) : basicScore;
    }

    @Override
    public int getId() {
        return this.id;
    }
}

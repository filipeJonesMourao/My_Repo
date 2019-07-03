public class Genie {

    int maxWish;
    int wishCount;
    private boolean recycled = false;
    private int recycledCount = 0;

    public Genie(){
        this.maxWish = (int) Math.ceil(Math.random()*10);
    }

    public void grantWish(){
        wishCount++;
    }

    public int getWishCount(){
        return wishCount;
    }

    public void recycle(){
        recycled = true;
        recycledCount++;
    }

    public boolean getRecycled(){
        return recycled;
    }
}

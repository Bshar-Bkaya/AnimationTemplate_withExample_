
package AnimationTemplate_withExample_;
public class nextDotLocation{
        private int x,y;
        nextDotLocation(){
        x=0;
        y=0;
        }
        public int getX() {
            return x;
        }
        
        public int getY(){
            return y;
        }

        public void set(int x,int y) {
            this.x += x;
            this.y += y;
        }
        public void checkNextMove(){
            if(x>340) x=340;
            else if(x<0) x=0;
            
            if(y>340) y=340;
            else if(y<0)  y=0;
        }

        
    }
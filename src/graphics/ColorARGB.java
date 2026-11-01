package graphics;

public final class ColorARGB {
    final int color;
    public ColorARGB(int R, int G, int B)
    {
        this.color=(255 << 24) | (R << 16) | (G<<8)|B;
    }
    public ColorARGB(float r, float g, float b)
    {
        int R=(int) r;
        int G=(int) g;
        int B=(int) b;
        this.color=(255 << 24) | (R << 16) | (G<<8)|B;
    }
    public ColorARGB(float a,float r, float g, float b)
    {
        int A=(int) a;
        int R=(int) r;
        int G=(int) g;
        int B=(int) b;
        this.color=(A << 24) | (R << 16) | (G<<8)|B;
    }
    public ColorARGB(int A, int R, int G, int B)
    {
        this.color=(A << 24) | (R << 16) | (G<<8)|B;
    }
    public ColorARGB(int color){
        this.color=color;
    }
    public int toInt()
    {
        return color;
    }
    public int alpha(){
        return color>>24&0xFF;
    }
    public int red()
    {
        return color>>16&0xFF;
    }
    public int green()
    {
        return color>>8&0xFF;
    }
    public int blue()
    {
        return color&0xFF;
    }
    public ColorARGB blend(ColorARGB color, float c){
        float a=alpha()*c+color.alpha()*(1-c);
        float r=red()*c+color.red()*(1-c);
        float g=green()*c+color.green()*(1-c);
        float b=blue()*c+color.blue()*(1-c);
        return new ColorARGB(a,r,g,b);
    }
    public ColorARGB blend(ColorARGB color, int c){
        int a=alpha()*c+color.alpha()*(1-c);
        int r=red()*c+color.red()*(1-c);
        int g=green()*c+color.green()*(1-c);
        int b=blue()*c+color.blue()*(1-c);
        return new ColorARGB(a,r,g,b);
    }
    public ColorARGB blend(ColorARGB color){
        int a=alpha()/2+color.alpha()/2;
        int r=red()/2+color.red()/2;
        int g=green()/2+color.green()/2;
        int b=blue()/2+color.blue()/2;
        return new ColorARGB(a,r,g,b);
    }
    public ColorARGB add(ColorARGB color){
        int a=alpha()+color.alpha();
        int r=red()+color.red();
        int g=green()+color.green();
        int b=blue()+color.blue();
        return new ColorARGB(a,r,g,b);
    }
    public ColorARGB saturated(float c){
        float a=alpha()*c;
        float r=red()*c;
        float g=green()*c;
        float b=blue()*c;
        return new ColorARGB(a,r, g, b);
    }

    public boolean equals(ColorARGB color) {
        if (this.color == color.toInt()) {
            return  true;
        }
        else {
            return false;
        }
    }
    public boolean equals(int color) {
        if (this.color == color) {
            return  true;
        }
        else {
            return false;
        }
    }
    public String toString(){
        String s="["+alpha()+", "+red()+", "+green()+", "+blue()+"]";
        return s;
    }
    public final static ColorARGB BLACK = new ColorARGB(0, 0, 0);
    public final static ColorARGB GREY = new ColorARGB(128, 128, 128);
    public final static ColorARGB WHITE = new ColorARGB(255, 255, 255);
    public final static ColorARGB RED = new ColorARGB(255, 0, 0);
    public final static ColorARGB GREEN = new ColorARGB(0, 255, 0);
    public final static ColorARGB BLUE = new ColorARGB(0, 0, 255);
    public final static ColorARGB YELLOW = new ColorARGB(255, 255, 0);
    public final static ColorARGB ORANGE= new ColorARGB(255, 128, 0);
    public final static ColorARGB OCHRE =new ColorARGB(204, 119, 34);
    public final static ColorARGB PINK=new ColorARGB(255, 192, 203);
    public final static ColorARGB HOT_PINK=new ColorARGB(255, 15, 192);
    public final static ColorARGB PURPLE=new ColorARGB(191, 0, 255);
    public final static ColorARGB VIOLET=new ColorARGB(128, 0, 255);
    public final static ColorARGB LIME=new ColorARGB(191, 255, 0);
    public final static ColorARGB TURQUOISE=new ColorARGB(64, 224, 208);
    public final static ColorARGB SKY_BLUE=new ColorARGB(135, 206, 235);
    public final static ColorARGB CELESTE=new ColorARGB(178, 255, 255);
    public final static ColorARGB MAGENTA=new ColorARGB(255, 0, 255);
    public final static ColorARGB CYAN=new ColorARGB(0, 255, 255);
    public final static ColorARGB BROWN=new ColorARGB(128, 64, 48);
    public final static ColorARGB BEIGE=new ColorARGB(245, 245, 220);
    public final static ColorARGB KHAKI=new ColorARGB(195, 176, 145);
    public final static ColorARGB RASPBERRY=new ColorARGB(227, 11, 93);
    public final static ColorARGB RUST=new ColorARGB(183, 65, 14);
}

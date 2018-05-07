/*
 * Decompiled with CFR 0_114.
 */
package mapGenerator;

import java.awt.Color;

public class Vec2 {
    public int x;
    public int y;
    public Color c;
	public boolean especial;


    public Vec2(int x, int y,boolean especial) {
        this.x = x;
        this.y = y;
		this.especial = especial;
    }
    public Vec2(int x, int y,Color c,boolean especial) {
        this.x = x;
        this.y = y;
		this.c = c;
		this.especial = especial;
    }
	public boolean isEspecial() {
		return especial;
	}
    
}


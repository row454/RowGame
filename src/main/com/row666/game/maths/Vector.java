package main.com.row666.game.maths;

public class Vector {
    public float x, y;
    
    public static final Vector ZERO = new Vector();
    public static final Vector LEFT = new Vector(-1, 0);
    public static final Vector RIGHT = new Vector(1, 0);
    public static final Vector UP = new Vector(0, -1);
    public static final Vector DOWN = new Vector(0, 1);
    public static final Vector X = new Vector(1, 0);
    public static final Vector Y = new Vector(0, 1);
    
    
    
    public Vector() {
        this(0, 0);
    }
    
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    
    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        
        return this;
    }
    public Vector zero() {
        this.x = 0;
        this.y = 0;
        return this;
    }
    
    public static Vector sum(Vector v1, Vector v2) {
        return new Vector().add(v1).add(v2);
    }
    
    public float len() {
            return (float) Math.sqrt(x * x + y * y);
    }
    
    public float len2() {
        return x * x + y * y;
    }
    
    
    public Vector sub(Vector v) {
        x -= v.x;
        y -= v.y;
        
        return this;
    }
    
    
    public Vector scale(Vector v) {
        x *= v.x;
        y *= v.y;
    
        return this;
    }
    
    public Vector scale(float scalar) {
        x *= scalar;
        y *= scalar;
        
        return this;
    }
    
    public Vector sign() {
        x = Math.signum(x);
        y = Math.signum(y);
        return this;
    }
    
    public Vector signGate(Vector gate) {
        Vector sign = new Vector(this).sign();
        Vector gateSign = new Vector(gate).sign();
        sign.scale(gateSign);
        
        this.x *= sign.x >= 0 ? 1 : 0;
        this.y *= sign.y >= 0 ? 1 : 0;
        
        return this;
        
    }
    
    public boolean equals(Vector v) {
        return v.x == x && v.y == y;
    }
    
    
}

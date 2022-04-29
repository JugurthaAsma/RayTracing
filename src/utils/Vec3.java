package utils;

/**
 *
 * @author Hylia
 */
public class Vec3 {

    public double x,y,z;

    public Vec3(final double x,final double y,final double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Vec3(final Vec3 that)
    {
            this.x=that.x;
            this.y=that.y;
            this.z=that.z;
    }

    public double lengthSquare()
    {
        return this.x*this.x + this.y*this.y + this.z*this.z;
    }

    public double length()
    {
        return Math.sqrt(this.lengthSquare());
    }

    public void setNormalize()
    {
        double l=this.lengthSquare();
        if (l > 0D) {
            double length = Math.sqrt(l);
            this.x /= length;
            this.y /= length;
            this.z /= length;
        }
    }
    
    public Vec3 normalize()
    {
        return (this.lengthSquare() == 0D) ? this : this.scale(1D / this.length());

    }

    public Vec3 add(final Vec3 that)
    {
        return new Vec3(
            this.x+that.x,
            this.y+that.y,
            this.z+that.z
        );
    }

    public Vec3 sub(final Vec3 that)
    {
        return new Vec3(
            this.x-that.x,
            this.y-that.y,
            this.z-that.z
        );
    }

    public Vec3 scale(final double scale)
    {
        return new Vec3(
            this.x*scale,
            this.y*scale,
            this.z*scale
        );
    }
    
    public void setScale(final double scale)
    {
        this.x*=scale;
        this.y*=scale;
        this.z*=scale;
    }

    public double dotProduct(final Vec3 v)
    {
        return this.x*v.x + this.y*v.y + this.z*v.z;
    }

}

package tools;

/**
 *
 * @author JUGURTHA
 */
public class Vec3d
{
    /**
     * x, y and z values of the current vector.
     * These are public to allow fast access and simple use.
     */
    public double x,y,z;

    /**
     * Default Constructor
     */
    public Vec3d()
    {
        this.x=this.y=this.z=0.0D;
    }

    /**
     * Constructor with initialisation
     * @param x,y,z values to place into current vector
     */
    public Vec3d(final double x,final double y,final double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * Constructor by copy
     * @param that vector to be copied in current vector
     */
    public Vec3d(final Vec3d that)
    {
            this.x=that.x;
            this.y=that.y;
            this.z=that.z;
    }

    /**
     * @return square of the length of current vector
     */
    public double lengthSquare()
    {
        return this.x*this.x + this.y*this.y + this.z*this.z;
    }
    
    /**
     * @return length of current vector
     */
    public double length()
    {
        return Math.sqrt(this.lengthSquare());
    }

    /**
     * Normalize current vector
     */
    public void setNormalize()
    {
        double l=this.lengthSquare();
        if (l > 0.0D) {
            double length = Math.sqrt(l);
            this.x /= length;
            this.y /= length;
            this.z /= length;
        }
    }
    
    public Vec3d normalize()
    {
        return (this.lengthSquare() == 0.0D) ? this : this.scale(1.0D / this.length());

    }

    /**
     * Add a vector to current vector
     * @param that any vector
     * @return current vector
     */
    public Vec3d add(final Vec3d that)
    {
        return new Vec3d(
            this.x+that.x,
            this.y+that.y,
            this.z+that.z
        );
    }

    /**
     * Subtract a vector to current vector
     * @param that vector to subtract
     * @return current vector
     */
    public Vec3d sub(final Vec3d that)
    {
        return new Vec3d(
            this.x-that.x,
            this.y-that.y,
            this.z-that.z
        );
    }

    /**
     * Scale current vector uniformly
     * @param scale uniform scale factor
     * @return current vector
     */
    public Vec3d scale(final double scale)
    {
        return new Vec3d(
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

    /**
     * Compute dot (inner) product with another vector
     * @param v vector with which dotproduct is computed
     * @return result of dot product
     */
    public double dotProduct(final Vec3d v)
    {
        return this.x*v.x + this.y*v.y + this.z*v.z;
    }

}

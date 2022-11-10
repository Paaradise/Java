package Algebra;

public class Vector
{
    public float x;

    public float y;

    public float z;

    public Vector( float x, float y, float z )
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add( Vector vector )
    {
        return new Vector( this.x + vector.x, this.y + vector.y, this.z + vector.z );
    }

    public Vector subtract( Vector vector )
    {
        return new Vector( this.x - vector.x, this.y - vector.y, this.z - vector.z );
    }

    public Vector multiply( float multiplier )
    {
        return new Vector( this.x * multiplier, this.y * multiplier, this.z * multiplier );
    }

    public float dot( Vector vector )
    {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    public Vector cross( Vector vector )
    {
        return new Vector(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x
        );
    }

    @Override
    public String toString()
    {
        return String.format("x: %f y: %f z: %f", this.x, this.y, this.z );
    }
}

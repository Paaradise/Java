package Polymorphism;

import java.awt.*;
import java.util.Map;

public class AdherentRectangle extends Rectangle
{
    public AdherentRectangle(Point vertex, int length, int height )
    {
        super( vertex.x, vertex.y , length, height );
    }

    public boolean adheres( AdherentRectangle rectangle )
    {
        var vertices = Map.of(
        "top-left", new Point( rectangle.x, rectangle.y ),
        "top-right", new Point( rectangle.x + rectangle.width, rectangle.y ),
        "bottom-left", new Point( rectangle.x, rectangle.y + rectangle.height ),
        "bottom-right", new Point( rectangle.x + rectangle.width, rectangle.y + rectangle.height )
        );

        var thisVertices = Map.of(
        "top-left", new Point( this.x, this.y ),
        "top-right", new Point( this.x + this.width, this.y ),
        "bottom-left", new Point( this.x, this.y + this.height ),
        "bottom-right", new Point( this.x + this.width, this.y + this.height )
        );

        // Top alignment
        //
        if ( vertices.get( "bottom-left" ).equals( thisVertices.get( "top-left" ) ) &&
             vertices.get( "bottom-right").equals( thisVertices.get( "top-right" ) ) )
        {
            return true;
        }

        // Left alignment
        //
        if ( vertices.get( "top-right" ).equals( thisVertices.get( "top-left" ) ) &&
             vertices.get( "bottom-right" ).equals( thisVertices.get( "bottom-left" ) ) )
        {
            return true;
        }

        // Bottom alignment
        //
        if ( vertices.get( "top-left" ).equals( thisVertices.get( "bottom-left" ) ) &&
             vertices.get( "top-right" ).equals( thisVertices.get( "bottom-right" ) ) )
        {
            return true;
        }

        // Right alignment
        //
        return vertices.get( "top-left" ).equals(thisVertices.get( "top-right" ) ) &&
               vertices.get( "bottom-left" ).equals(thisVertices.get( "bottom-right" ) );
    }
}

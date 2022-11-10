package Standard;

import java.util.Map;

public class CurrencyConverter
{
    private Map< String, Float > rates;

    public CurrencyConverter( Map< String, Float > rates )
    {
        this.rates = rates;
    }

    public float convert( String source, String target, float value )
    {
        var fromRate = rates.get( source );
        var toRate = rates.get( target );
        var rate = fromRate / toRate;

        return value * rate;
    }
}

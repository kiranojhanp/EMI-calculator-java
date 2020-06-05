# BMI Calculator



## Output

![This is what application looks like](https://i.imgur.com/heTkY7l.jpg)

## Creating an UI

![UI](https://i.imgur.com/lHtlrGx.jpg)
Constraint layout was used to build the UI.

## Getting value from textfields

In MainActivity.java, We are going to make variables of textfield and textview first:
```java
final EditText principal = (EditText) findViewById(R.id.editTextweight);
final EditText rate = (EditText) findViewById(R.id.editTextheight);
final EditText time = (EditText) findViewById(R.id.editTextTime);
final TextView result = (TextView) findViewById(R.id.textView3);
```

then we're going to get the values and convert them to string
```java
String pl = principal.getText().toString();
String rt = rate.getText().toString();
String tm = time.getText().toString();
```

a null validation is performed
```java
 // validation
 if (TextUtils.isEmpty(pl)){
 weight.setError("Please enter your weight");
 weight.requestFocus();
 return;
 }

 if (TextUtils.isEmpty(rt)){
 height.setError("Please enter your weight");
 height.requestFocus();
 return;
 }

if (TextUtils.isEmpty(tm)){
time.setError("Please enter your weight");
time.requestFocus();
return;
}
```

#### The formula to calculate EMI is,

![emi firmula](https://i.imgur.com/4BhoMs9.png)

So, We'll be calculating this making different methods to make it easier

## Methods

```java
// calculate rate
    public float calInt(float i) {
        return (float)(i / 12 / 100);
    }

    // numerator
    public float calFinalNume(float months, float Rate) {
        return (float)(Math.pow(1 + Rate, months));
    }

    // denominator
    public float calFinalDeno(float months, float Rate) {
        return (float)(Math.pow(1 + Rate, months ) - 1);
    }

    // divide
    public float divideNumeandDeno(float nume, float deno){
        return (float) (nume/deno);
    }


    // Calculate EMI
    private float calculateEMI(float principal, float rate, float dividedvalue){
        return (float) (principal * rate * dividedvalue);
    }
```


If the value aren't null, It is sent for calculation using above methods.


```java
// Get the user values and parse them to float
// Get the user values and parse them to float
float ppl = Float.parseFloat(pl);
float rat = calInt(Float.parseFloat(rt));
float tim = Float.parseFloat(tm);


float nume = calFinalNume(tim, rat);
float deno = calFinalDeno(tim, rat);
float dividedvalue = divideNumeandDeno(nume, deno);

float emiValue = calculateEMI(ppl, rat, dividedvalue);

```

We're asked to display amount in two decimal place so, We have to use DecimalFormat object from core java.

```java
// Rounding up and displaying in 2 dec place
DecimalFormat df = new DecimalFormat("0.00");
df.setRoundingMode(RoundingMode.UP);

result.setText(String.valueOf("EMI: Rs. " + df.format(emiValue)));
```

In this way, EMI Calculator was created

## Youtube video
[YOUTUBE](https://youtu.be/uq9HI4lfm_E)


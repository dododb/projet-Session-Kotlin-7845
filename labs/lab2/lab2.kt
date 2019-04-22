public fun main(args: Array<String>) {
  //recette1() 
  //recette2()
  recette3()
  //recette4()
}

public fun recette1()
{
    var b = Banane() 
    var f = Fraise() 
    exemple_banane() 
    exemple_fruit(b) 
    exemple_fruit(f) 
}

public fun recette2()
{
  var  b = Banane() 
  var f = Fraise() 
  exemple_banane_p() 
  exemple_fruit_p(b) 
  exemple_fruit_p(f) 
}

public fun recette3()
{
  brochette_fp_fruit( Banane()) 
  brochette_fp_fruit( Fraise()) 
}

public fun recette4()
{
  var brochette_fruit = BrochetteGen<Fruit>() 
  brochette_fruit.embrocher( Banane()) 
  brochette_fruit.embrocher( Orange()) 
  brochette_fruit.embrocher( Fraise()) 

  var brochette_banane = BrochetteGen<Banane>() 
  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 

  compte_sloubifuit(brochette_fruit) 
  compte_sloubifuit(brochette_banane) 


  var brochette_fruitAPeler = BrochetteGen<FruitAPeler>() 
  brochette_fruitAPeler.embrocher( Banane()) 
  brochette_fruitAPeler.embrocher( Orange()) 
  brochette_fruitAPeler.embrocher( Banane()) 

  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 

  brochette_fruit.embrocher( Banane()) 
  brochette_fruit.embrocher( Orange()) 
  brochette_fruit.embrocher( Fraise()) 

  pele_mele(brochette_fruitAPeler) 
  pele_mele(brochette_banane) 
  //pele_mele(brochette_fruit)  //plante ici, etant donnée qu

  brochette_fruitAPeler.embrocher( Banane()) 
  brochette_fruitAPeler.embrocher( Orange()) 
  brochette_fruitAPeler.embrocher( Banane()) 

  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 
  brochette_banane.embrocher( Banane()) 

  brochette_fruit.embrocher( Banane()) 
  brochette_fruit.embrocher( Orange()) 
  brochette_fruit.embrocher( Fraise()) 

  appel_a_peau(brochette_fruitAPeler) 
  appel_a_peau(brochette_fruit) 
  //appel_a_peau(brochette_banane)  //erreur statique
} 

public fun exemple_banane()
{
    var brochette = Brochette() 
    var banane = Banane() 
    brochette.embrocher(banane) 
    var f = brochette.debrocher() as FruitAPeler
    f.peler() 
}

public fun exemple_fruit(fruit: Fruit)
{
    var brochette = Brochette() 
    brochette.embrocher(fruit as FruitAPeler) 
    var f = brochette.debrocher() as FruitAPeler // erreur dynamique ici au niveau du cast car le fruit récupéré n'est pas de type dynamique FruitAPeler
    f.peler() 
}

public fun exemple_banane_p()
{
    var brochette = BrochetteGen<FruitAPeler>() 
    var banane = Banane() 
    brochette.embrocher(banane) 
    var f = brochette.debrocher() 
    f?.peler() 
}

public fun exemple_fruit_p(fruit: Fruit)
{
    var brochette = BrochetteGen<FruitAPeler>() 
    //brochette.embrocher(fruit)  // erreur statique ici car fruit et de type statique fruit et pas de type fruitAPeler
    var f = brochette.debrocher() 
    f?.peler() 
}

public fun brochette_fp_fruit(fruit: Fruit)
{
    var brochette_p = BrochetteGen<FruitAPeler>() 
    var obj = brochette_p as Any
    var brochette_f = obj as BrochetteGen<Fruit>
    brochette_f.embrocher(fruit) 
    brochette_p.debrocher()?.peler() //fraise cannot be cast to fruitAPeler
}

public fun compte_sloubifuit(brochette: IBrochetteGenOut<Fruit>)
{
    var count = 0;
    var f = brochette.debrocher();
    while(f != null)
    {
        count++;
        f = brochette.debrocher();
    }
    println("Nombre de fruit debroché : $count");
}

public fun pele_mele(brochette: IBrochetteGenOut<FruitAPeler>)
{
    var count = 0;
    var f = brochette.debrocher();
    while(f != null)
    {
        f.peler();
        count++;
        f = brochette.debrocher();
    }
    println("Nombre de fruit debroché et peler : $count");
}

public fun appel_a_peau(brochette: IBrochetteGenIn<FruitAPeler> )
{
    brochette.embrocher(Orange());
    brochette.embrocher(Banane());
}

public open class Fruit
{

}

public open class FruitAPeler : Fruit()
{
	public fun peler() {}
}

public class Banane : FruitAPeler() {}
public class Orange : FruitAPeler() {}
public class Fraise : Fruit() {}

public class Brochette
{
	var fruits: ArrayList<Fruit> = ArrayList<Fruit>() 
	public fun embrocher(f: Fruit)
	{
		fruits.add(0, f) 
	}
	public fun debrocher(): Fruit?
	{
		if(fruits.size == 0) return null 
		var f: Fruit = fruits.get(0)
		//fruits.removeAt(0) //peut etre ?
		return f 
	}
}

public class BrochetteGen<T : Fruit> : IBrochetteGenOut<T>, IBrochetteGenIn<T>
{
    var fruits = ArrayList<T>();
    public override fun embrocher(f: T)
    {
        fruits.add(0,f);
    }
    public override fun debrocher(): T?
    {
        if(fruits.size==0) return null
        var myFruit = fruits.get(0);
        fruits.removeAt(0);
        return myFruit;
    }
}


public interface IBrochetteGenOut<out T>
{
	fun debrocher(): T?
}

public interface IBrochetteGenIn<in T>
{
	fun embrocher(f: T) 
}
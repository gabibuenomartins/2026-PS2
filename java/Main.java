public class Main {

    public static void main(String[] args) {
       
        int[] numerosInt = {8, 3, 10, 5, 12};
        double[] numerosDouble = {8.5, 3.2, 10.0, 5.7, 12.4};
        float[] numerosFloat = {8.5f, 3.2f, 10.0f, 5.7f, 12.4f};

        System.out.println(" TESTES DOS RESULTADOS \n");

        //  CALCULASOMA
        System.out.println("1️⃣ calculaSoma:");
        System.out.println("   [int + for]: " + calculaSomaInt(numerosInt));
        System.out.println("   [double + for-each]: " + calculaSomaDouble(numerosDouble));
        System.out.println("   [float + while]: " + calculaSomaFloat(numerosFloat));

        // CALCULAMEDIA
        System.out.println("\n2️⃣ calculaMedia:");
        System.out.println("   [int + for-each]: " + calculaMediaInt(numerosInt));
        System.out.println("   [double + while]: " + calculaMediaDouble(numerosDouble));
        System.out.println("   [float + for]: " + calculaMediaFloat(numerosFloat));

        //  MENORVALOR
        System.out.println("\n3️⃣ menorValor:");
        System.out.println("   [int + while]: " + menorValorInt(numerosInt));
        System.out.println("   [double + for-each]: " + menorValorDouble(numerosDouble));
        System.out.println("   [float + for]: " + menorValorFloat(numerosFloat));

        // 4. MAIORVALOR
        System.out.println("\n4️⃣ maiorValor:");
        System.out.println("   [int + for-each]: " + maiorValorInt(numerosInt));
        System.out.println("   [double + for]: " + maiorValorDouble(numerosDouble));
        System.out.println("   [float + while]: " + maiorValorFloat(numerosFloat));

        // CONTARACIMA
        System.out.println("\n5️⃣ contarAcima (limite = 6):");
        System.out.println("   [int + for]: " + contarAcimaInt(numerosInt, 6));
        System.out.println("   [double + while]: " + contarAcimaDouble(numerosDouble, 6.0));
        System.out.println("   [float + for-each]: " + contarAcimaFloat(numerosFloat, 6.0f));
    }

    // CALCULASOMA 
    public static int calculaSomaInt(int[] numeros) {
        int soma = 0;
        for (int i = 0; i < numeros.length; i++) soma += numeros[i];
        return soma;
    }

    public static double calculaSomaDouble(double[] numeros) {
        double soma = 0.0;
        for (double num : numeros) soma += num;
        return soma;
    }

    public static float calculaSomaFloat(float[] numeros) {
        float soma = 0.0f;
        int i = 0;
        while (i < numeros.length) {
            soma += numeros[i];
            i++;
        }
        return soma;
    }

    // CALCULAMEDIA 
    public static int calculaMediaInt(int[] numeros) {
        int soma = 0;
        for (int num : numeros) soma += num;
        return soma / numeros.length;
    }

    public static double calculaMediaDouble(double[] numeros) {
        double soma = 0.0;
        int i = 0;
        while (i < numeros.length) {
            soma += numeros[i];
            i++;
        }
        return soma / numeros.length;
    }

    public static float calculaMediaFloat(float[] numeros) {
        float soma = 0.0f;
        for (int i = 0; i < numeros.length; i++) soma += numeros[i];
        return soma / numeros.length;
    }

    // MENORVALOR 
    public static int menorValorInt(int[] numeros) {
        int menor = numeros[0];
        int i = 1;
        while (i < numeros.length) {
            if (numeros[i] < menor) menor = numeros[i];
            i++;
        }
        return menor;
    }

    public static double menorValorDouble(double[] numeros) {
        double menor = numeros[0];
        for (double num : numeros) {
            if (num < menor) menor = num;
        }
        return menor;
    }

    public static float menorValorFloat(float[] numeros) {
        float menor = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < menor) menor = numeros[i];
        }
        return menor;
    }

    // MAIORVALOR
    public static int maiorValorInt(int[] numeros) {
        int maior = numeros[0];
        for (int num : numeros) {
            if (num > maior) maior = num;
        }
        return maior;
    }

    public static double maiorValorDouble(double[] numeros) {
        double maior = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > maior) maior = numeros[i];
        }
        return maior;
    }

    public static float maiorValorFloat(float[] numeros) {
        float maior = numeros[0];
        int i = 1;
        while (i < numeros.length) {
            if (numeros[i] > maior) maior = numeros[i];
            i++;
        }
        return maior;
    }

    public static int contarAcimaInt(int[] numeros, int limite) {
        int contador = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > limite) contador++;
        }
        return contador;
    }

    public static int contarAcimaDouble(double[] numeros, double limite) {
        int contador = 0;
        int i = 0;
        while (i < numeros.length) {
            if (numeros[i] > limite) contador++;
            i++;
        }
        return contador;
    }

    public static int contarAcimaFloat(float[] numeros, float limite) {
        int contador = 0;
        for (float num : numeros) {
            if (num > limite) contador++;
        }
        return contador;
    }
}
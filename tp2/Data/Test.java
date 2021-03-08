package tp2.Test;

import tp2.Data;

import org.junit.Test;

public class MainTest {

    @Test
    public void testeDeConstrutorComHoraValido() {
      Data objeto = new Data(2021, 3, 7, 10, 15);
      assert objeto.printSelf().equals("2021-03-7 10:15");
    }

    @Test
    public void testeDeConstrutorSemHoraValido() {
      Data objeto = new Data(2021, 3, 7);
      assert objeto.printSelf().equals("2021-03-07");
    }

    @Test
    public void testeDeConstrutorInvalidoAno() {
      Throwable e = null;
      try {
        Data objeto = new Data(1899, 3, 7);
      }
      catch (Throwable ex) {
        e = ex;
      }
      assert e instanceof Exception;
    }

    @Test
    public void testeDeConstrutorInvalidoMes() {
      Throwable e = null;
      try {
        Data objeto = new Data(2021, 23, 7);
      }
      catch (Throwable ex) {
        e = ex;
      }
      assert e instanceof Exception;
    }

    @Test
    public void testeDeConstrutorInvalidoDia() {
      Throwable e = null;
      try {
        Data objeto = new Data(2021, 3, 37);
      }
      catch (Throwable ex) {
        e = ex;
      }
      assert e instanceof Exception;
    }

    @Test
    public void testeDeConstrutorInvalidoHora() {
      Throwable e = null;
      try {
        Data objeto = new Data(2021, 3, 7, 33, 0);
      }
      catch (Throwable ex) {
        e = ex;
      }
      assert e instanceof Exception;
    }

    @Test
    public void testeDeConstrutorInvalidoMinuto() {
      Throwable e = null;
      try {
        Data objeto = new Data(2021, 3, 7, 3, 80);
      }
      catch (Throwable ex) {
        e = ex;
      }
      assert e instanceof Exception;
    }

    @Test
    public void testeAdicionarData() {
      Data objeto = new Data(2021, 3, 7);
      objeto.addDias(5);
      assert objeto.printSelf().equals("2021-03-12");
    }

    @Test
    public void testeAdicionarDataSobreMes() {
      Data objeto = new Data(2021, 3, 7);
      objeto.addDias(25);
      assert objeto.printSelf().equals("2021-04-01");
    }

    @Test
    public void testeDifDatas() {
      Data objeto = new Data(2021, 3, 8);
      Data objeto2 = new Data(2021, 3, 7);

      float expectedResult = 1;

      assert objeto.getDiferenca(objeto2).equals(expectedResult);
    }

    @Test
    public void testeMaisRecente() {
      Data objeto = new Data(2021, 3, 8);
      Data objeto2 = new Data(2021, 3, 7);

      float expectedResult = 1;

      assert objeto.compareTo(objeto2).equals(false);
    }
}
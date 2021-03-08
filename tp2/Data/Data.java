package tp2.Data;

public class Data {
  
  int ano;
  int mes;
  int dia;
  int hora;
  int minuto;

  public Data(int ano, int mes, int dia, int hora, int minuto) throws Exception {
    // Construtor recebendo todos os parâmetros
    validateData(ano, mes, dia, hora, minuto);

    this.ano = ano;
    this.mes = mes;
    this.dia = dia;
    this.hora = hora;
    this.minuto = minuto;
  }
  public Data(int ano, int mes, int dia) throws Exception {
    // Construtor sem receber hora e minuto
    validateData(ano, mes, dia, 0, 0);

    this.ano = ano;
    this.mes = mes;
    this.dia = dia;
    this.hora = 0;
    this.minuto = 0;
  }

  private void validateData(int ano, int mes, int dia, int hora, int minuto) throws Exception {
    // Valida a data e, em caso de data inválida, levanta uma exception
    boolean isValid = true;
    // Valida ano
    isValid = ano < 1900 ? false : isValid;
    // Valida mês
    isValid = 1 > mes || mes > 12 ? false : isValid;
    // Valida dia
    isValid = dia > getUltimoDiaMes(ano, mes) ? false : isValid;
    // Valida hora
    isValid = 0 > hora || hora > 23 ? false : isValid;
    // Valida minuto
    isValid = 0 > minuto || minuto > 59 ? false : isValid;

    if(!isValid) {
      throw new Exception("Data inválida");
    }
  }


  private int getUltimoDiaMes(int ano, int mes) {
    // Retorna qual o último dia do mês
    int maxDay = 31;

    if(mes == 4 || mes == 6 || mes == 9 || mes == 11) {
      maxDay = 30;
    }
    else if(mes == 2) {
      maxDay = isAnoBissexto(ano) ? 29 : 28;
    }

    return maxDay;
  }


  private boolean isAnoBissexto(int ano) {
    // Retorna se o ano é bissexto
    return (ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0));
  }


  private long getMinutos() {
    // Retorna a data em minutos a partir do ano 1900
    long totalMinutos = 0;
    int deltaAnos = this.ano - 1900;
    // Adiciona ano
    totalMinutos += deltaAnos * (isAnoBissexto(this.ano) ? 527040 : 525600);
    // Adiciona mês anterior
    totalMinutos += (this.mes - 1) * getUltimoDiaMes(this.ano, this.mes - 1) * 1440;
    // Adiciona dias do mês
    totalMinutos += (this.dia - 1) * 1440;
    // Adiciona horas
    totalMinutos += this.hora * 60;
    // Adiciona minutos
    totalMinutos += this.minuto;

    return totalMinutos;
  }


  public void setFromMinutos(long totalMinutos) {
    // Define a data a partir da quantidade de minutos passados desde o início do ano de 1900

    // Zera dados
    this.ano = 1900;
    this.mes = 1;
    this.dia = 1;
    this.hora = 0;
    this.minuto = 0;

    // Seta ano
    while(totalMinutos >= 527040) {
      boolean isBissexto = isAnoBissexto(this.ano);
      totalMinutos -= isBissexto ? 527040 : 525600;
      this.ano++;
    }

    // Seta mês
    while(totalMinutos >= getUltimoDiaMes(this.ano, this.mes) * 1440) {
      totalMinutos -= getUltimoDiaMes(this.ano, this.mes) * 1440;
      this.mes++;
    }

    // Seta dia
    while(totalMinutos >= 1440) {
      this.dia++;
      totalMinutos -= 1440;
    }

    // Seta hora
    while(totalMinutos >= 60) {
      this.hora++;
      totalMinutos -= 60;
    }

    // Seta minuto
    this.minuto = Math.toIntExact(totalMinutos);
  }


  public void addDias(int dias) {
    // Adiciona dias à data
    long totalMinutos = this.getMinutos();
    this.setFromMinutos(totalMinutos + dias * 1440);
  }


  public float getDiferenca(Data outra) {
    // Retorna a diferença entre datas, em dias
    long thisMinutos = this.getMinutos();
    long outraMinutos = outra.getMinutos();
    
    long difMinutos = thisMinutos - outraMinutos;
    return (float)difMinutos / 1440;
  }


  public boolean compareTo(Data outra) {
    // Retorna se a data recebida é mais recente
    return this.getMinutos() > outra.getMinutos();
  }


  public String printSelf() {
    return this.ano + "-" + this.mes + "-" + this.dia + " " + this.hora + ":" + this.minuto;
  }
}

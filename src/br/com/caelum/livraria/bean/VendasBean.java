package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

	public List<Venda> getVendas(long seed) {

		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();

		Random random = new Random(seed);
		for (Livro livro : livros) {

			Integer quantidade = random.nextInt(500);

			vendas.add(new Venda(livro, quantidade));

		}

		return vendas;
	}

	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries serie2017 = new ChartSeries();
		for (Venda venda : getVendas(1234)) {
			serie2017.setLabel("Vendas 2017");
			serie2017.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(serie2017);

		ChartSeries serie2016 = new ChartSeries();
		for (Venda venda : getVendas(4321)) {
			serie2016.setLabel("Vendas 2016");
			serie2016.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(serie2016);
		
		// código omitido...
	    model.setTitle("Vendas"); // setando o título do gráfico
	    model.setLegendPosition("ne"); // nordeste

	    // pegando o eixo X do gráfico e setando o título do mesmo
	    Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Título");

	    // pegando o eixo Y do gráfico e setando o título do mesmo
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");


		return model;
	}

}

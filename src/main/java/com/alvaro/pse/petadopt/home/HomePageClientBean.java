/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.home;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class HomePageClientBean {

    CartesianChartModel cartChart;

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    /*
    public void createBarModel() {
        ChartSeries rigs = new ChartSeries();

        Mascota[] rList = this.findAllMascotas();

        Map<Object, Number> rigMap = new HashMap<>();
        for (Mascota m : rList) {

            rigMap.put(m.getEstado(), m.getId());

            cartChart = new CartesianChartModel();
            rigs.setData(rigMap);
            cartChart.addSeries(rigs);

        }

    }
     */

 /*
    private Mascota[] findAllMascotas(){
        Mascota[] found = null;
        Response response = target.register(MascotaWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        if(response.getStatus() == 200){
            found = response.readEntity(Mascota[].class);
        }
        return found;
                
    }
     */
}

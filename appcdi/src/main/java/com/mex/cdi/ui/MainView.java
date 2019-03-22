package com.mex.cdi.ui;

import com.mex.cdi.modelo.Customer;
import com.mex.cdi.service.CustomerFacade;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import javax.ejb.EJB;

import javax.inject.Inject;

/**
 * The main view contains a simple label element and a template element.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow with CDI", shortName = "Project Base")
@HtmlImport("frontend://styles/shared-styles.html")
public class MainView extends VerticalLayout {

    @Inject
    private MessageBean messageBean;
    
    @EJB
    private CustomerFacade customerService;
    
       
    Grid<Customer> grid = new  Grid(Customer.class);
    
    public MainView() {
        
        setGrid();
                       
        Button button = new Button("Ver Customers",
                event ->  {
                    updateGrid();
                    Notification.show(messageBean.getMessage() + customerService.count() + " Registros");
                            }
                            
        
        );
        add(button,grid);
    }
    
    public void updateGrid() {
        grid.setItems(customerService.findAll());
    }

    private void setGrid() {
        grid.removeAllColumns();
        grid.addColumn(Customer::getCustomerId).setHeader("Código");
        grid.addColumn(Customer::getName).setHeader("Nombre Cliente");
        grid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
    }

}

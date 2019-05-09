/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dao.SolicitudDao;
import activos.logic.Solicitud;
import java.util.List;

/**
 *
 * @author grave
 */
public class Utils {
    
     public List<Solicitud> findAll() {
        List<Solicitud> solicitudes = null;
        SolicitudDao dao = new SolicitudDao();
        try {
            solicitudes = dao.findAll();
            return solicitudes;
        } catch (Exception ex) {
        }
        return null;
    }
}

package main;


import main.game.Palabra;

public class Utilities {


    public static void readJSON(final String ruta) {
    }

    public static void loadPalabras() {
        String[] palabras = {
                "Caja", "Bancos", "Inversiones temporales", "Mercancias", "Documentos por cobrar", "Deudores diversos",
                "Anticipo a proveedores", "Terrenos", "Edificios", "Equipo de computo", "Equipo de reparto", "Depositos en garantia",
                "Inversiones permanentes", "Activo circulante", "Activo diferido", "Activo fijo", "Pasivo diferido", "Pasivo circulante",
                "Pasivo fijo", "Gastos de invesitgacion", "Gastos preoperativos", "Gastos de mercadotecnia", "Gastos de organizacion",
                "Gastos de instalación", "Papeleria y utiles", "Primas de seguro", "Rentas pagadas por anticipado", "Intereses pagados por anticipado"};

        String[] concepto = {
                "Se deben presentar en primero y segundo lugar en el activo circulante por ser elementos que no necesitan ninguna transformación para ser efectivo", //caja
                "Se deben presentar en primero y segundo lugar en el activo circulante por ser elementos que no necesitan ninguna transformación para ser efectivo", //bancos
                "Inversiones de inmediata recuperación", //inversiones temporales
                "Se deben presenar en el activo circulante por su facil conversión en efectivo", //mercancías
                "Es de facil conversión en efectivo, regularmente su vencimiento es en corto plazo  ",//Documentos por Cobrar
                "Son de rápida recuperación debido a que la entidad les concede corto plazo para pagar",//Deudores diversos
                "Es en un plazo no mayor a un año y se debe presentar ejn el activo circulante",//Anticipo a proveedores
                "Se encuentra en activo fijo",//Terrenos
                "Donde se establece o labora la empresa",//Edificios
                "equ",//Equipo de computo
                "La entidad lo utiliza para distribuir su servicio o producto",//Equipo de reparto
                "Se entregan para garantizar el cumplimiento de obligaciones contratadas, no son objeto del impuesto al valor agregado.",//Depositos en garantia
                "Son aquellos fondos invertidos con la finalidad de mantener tal inversión en el largo plazo.",//Inversiones permanentes
                "Convertibles en efectivo en un plazo no mayor a un año",//Activo circulante
                "Recursos con cierta permanencia, adquiridos con la finalidad de usarlos, y no de venderlos",//Activo diferido
                "Gastos pagados por anticipado que generan el derecho de recibir un servicio o beneficio posterior",//Activo fijo
                "Se transforman en utilidad conforme se presta el servicio o transcurre el tiempo",//Pasivo diferido
                "Lo constituten las deudas y obligaciones a cargo de la entidadcon vencimiento menor a un año",//Pasivo circulante
                "Deudas y obligaciones a gargo de la empresa con vencimiento en un plazo mayor a un año",//Pasivo fijo
                "Lo constituyen los cobros anticipados efectuados por la entidad, proporciona un servicio en un plazo mayor a un año",//Gastos de invesitgacion
                "Se realizan antes del inicio de operaciones de una empresa",//Gastos preoperativos
                "Se van a pagar los gastos de investigación de mercado, canales de distribución e imagen de producto que se pague anticipadamente y son de naturaleza deudora.",//Gastos de mercadotecnia
                "son los costos iniciales en los que se incurre para crear una compañía",//Gastos de organizacion
                "Gastos que se hacen para acondicionar las instalaciones de acuerdo a las necesidades de operación de una Empresa, así como para darle cierta comodidad y presentación",//Gastos de instalación
                "Útiles que se utilizan en la oficina",//Papeleria y utiles
                "El precio que el asegurado paga por la cobertura que recibe del riesgo asegurado a su compañía de seguros",//Primas de seguro
                "Se debe cargar en las cuentas de gastos de venta y de gastos de administración",//Rentas pagadas por anticipado
                "Se recoge el importe de los intereses pagados por la empresa"//Intereses pagados por anticipado
        };

        for (int i = 0; i < palabras.length; i++) {
            Global.palabras.add(new Palabra(palabras[i], concepto[i]));
        }

    }
}

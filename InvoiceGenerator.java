import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * I N F 1 1 2 0
 *
 * InvoiceGenerator : Cette classe calcule et affiche les informations saisies
 * d'une facture de toiture et du nombre total
 * des recouvrements selon l'option choisie.
 * 
 * @author Kameha Dylan Labaste-Nauta
 * @version 25 octobre 2022
 *
 *          LABK02049404
 *          kg691119@ens.uqam.ca
 */

public class InvoiceGenerator {

    // Autres méthodes s'il y a lieu

    public static void main(String[] params) {

        // Déclaration des constantes
        final float TAUX_TPS = 0.05f;
        final float TAUX_TVQ = 0.09975f;
        final double PRIX_ELASTOMERE = 21.75;
        final double PRIX_TPO = 13.25;
        final double PRIX_EPDM = 12.50;
        final double PRIX_GRAVIER_BITUME = 12.25;
        final double PRIX_ASPHALTE = 7.50;
        final double PRIX_TOLE = 26.50;
        final double PRIX_ARDOISE = 36.25;
        final String TYPE_TOIT_PLAT = "Plat";
        final String TYPE_TOIT_PENTE = "En pente";
        final String TYPE_COUVERTURE_ELASTOMERE = "Membrane élastomère";
        final String TYPE_COUVERTURE_TPO = "Membrane TPO";
        final String TYPE_COUVERTURE_EPDM = "Membrane EPDM";
        final String TYPE_COUVERTURE_GRAVIER_BITUME = "Multicouche gravier et bitume";
        final String TYPE_COUVERTURE_ASPHALTE = "Bardeau en asphalte";
        final String TYPE_COUVERTURE_TOLE = "Tôle";
        final String TYPE_COUVERTURE_ARDOISE = "Ardoise";
        final String DUREE_VIE_ELASTOMERE = "30 ans";
        final String DUREE_VIE_TPO = "28 ans";
        final String DUREE_VIE_EPDM = "25 ans";
        final String DUREE_VIE_GRAVIER_BITUME = "23 ans";
        final String DUREE_VIE_ASPHALTE = "20 ans";
        final String DUREE_VIE_TOLE = "50 ans";
        final String DUREE_VIE_ARDOISE = "125 ans";
        final String PAIEMENT_CASH = "Cash";
        final String PAIEMENT_DEBIT = "Débit";
        final String PAIEMENT_CREDIT = "Crédit";

        // Déclaration des variables
        char choixMenu;
        String nomClient;
        String prenomClient;
        String telephoneClient;
        String adresseClient;
        char choixTypeToit;
        String typeToit;
        char choixTypeCouvertureToit;
        String typeCouvertureToit;
        String dureeVieCouverture;
        double surfacePiedCarre;
        double prixPiedCarre;
        char choixModePaiement;
        String modePaiement;
        double totalAvantTaxes;
        double montantTPS;
        double montantTVQ;
        double totalFacture;
        int numeroFacture = 0;
        double totalMontantRecouvrement = 0;
        int nombreRecouvrement = 0;

        // Ajout de la date du jour pour la facture
        LocalDateTime dateHeureSysteme = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Lancement du programme
        do {
            // Message de bienvenue
            System.out.println("\n----------------------------------------------------------------");
            System.out.println("Bienvenue dans le système de facturation de \"Toiture-pour-tous\"");
            System.out.println("----------------------------------------------------------------");

            // Affichage de la sélection des choix du menu
            System.out.println("\n    *** Menu de choix ***");
            System.out.println("    1. Facturer le recouvrement d'une toiture.");
            System.out.println(
                    "    2. Afficher le nombre de recouvrements et le montant total de tous les recouvrements.");
            System.out.println("    3. Quitter le programme.");
            System.out.print("\nEntrez votre choix : ");
            choixMenu = Clavier.lireCharLn();

            while (choixMenu != '1' && choixMenu != '2' && choixMenu != '3') {
                System.out.println("\nEntrée invalide !");
                System.out.println("\n  *** Menu de choix ***");
                System.out.println("    1. Facturer le recouvrement d'une toiture.");
                System.out.println(
                        "    2. Afficher le nombre de recouvrements et le montant total de tous les recouvrements.");
                System.out.println("    3. Quitter le programme.");
                System.out.print("\nEntrez votre choix : ");
                choixMenu = Clavier.lireCharLn();
            }

            // Menu choix numéro 1 : Saisie et affichage de la ou les factures
            if (choixMenu == '1') {
                System.out.print("\nEntrez le nom du client : ");
                nomClient = Clavier.lireString();
                System.out.print("\nEntrez le prénom du client : ");
                prenomClient = Clavier.lireString();
                System.out.print("\nEntrez le numéro de téléphone du client : ");
                telephoneClient = Clavier.lireString();
                System.out.print("\nEntrez l'adresse du client : ");
                adresseClient = Clavier.lireString();

                // Choix du type de toit
                System.out.println("\nEntrez le type de toit (1 pour le toit plat ou 2 pour le toit en pente) : ");
                choixTypeToit = Clavier.lireCharLn();

                while (choixTypeToit != '1' && choixTypeToit != '2') {
                    System.out.println("\nEntrée invalide !");
                    System.out.println("\nEntrez le type de toit (1 pour le toit plat ou 2 pour le toit en pente) : ");
                    choixTypeToit = Clavier.lireCharLn();
                }

                if (choixTypeToit == '1') {
                    typeToit = TYPE_TOIT_PLAT;
                } else {
                    typeToit = TYPE_TOIT_PENTE;
                }

                // Choix du type de couverture
                if (typeToit == TYPE_TOIT_PLAT) {
                    System.out.print("\nEntrez le type de couverture ( 1 pour la membrane élastomère," +
                            " 2 pour la membrane TPO," +
                            " 3 pour la membrane EPDM," +
                            " ou 4 pour le multicouche gravier et bitume) : ");
                    choixTypeCouvertureToit = Clavier.lireCharLn();

                    while (choixTypeCouvertureToit != '1' && choixTypeCouvertureToit != '2'
                            && choixTypeCouvertureToit != '3' && choixTypeCouvertureToit != '4') {
                        System.out.println("\nEntrée invalide !");
                        System.out.print("\nEntrez le type de couverture ( 1 pour la membrane élastomère," +
                                " 2 pour la membrane TPO," +
                                " 3 pour la membrane EPDM," +
                                " ou 4 pour le multicouche gravier et bitume) : ");
                        choixTypeCouvertureToit = Clavier.lireCharLn();
                    }
                } else {
                    System.out.print("\nEntrez le type de couverture ( 5 pour le bardeau en asphalte," +
                            " 6 pour la tôle," +
                            " 7 pour l'ardoise.) : ");
                    choixTypeCouvertureToit = Clavier.lireCharLn();

                    while (choixTypeCouvertureToit != '5' && choixTypeCouvertureToit != '6'
                            && choixTypeCouvertureToit != '7') {
                        System.out.println("\nEntrée invalide !");
                        System.out.print("\nEntrez le type de couverture ( 5 pour le bardeau en asphalte," +
                                " 6 pour la tôle," +
                                " 7 pour l'ardoise.) : ");
                        choixTypeCouvertureToit = Clavier.lireCharLn();
                    }
                }

                switch (choixTypeCouvertureToit) {
                    case '1':
                        typeCouvertureToit = TYPE_COUVERTURE_ELASTOMERE;
                        dureeVieCouverture = DUREE_VIE_ELASTOMERE;
                        prixPiedCarre = PRIX_ELASTOMERE;
                        break;
                    case '2':
                        typeCouvertureToit = TYPE_COUVERTURE_TPO;
                        dureeVieCouverture = DUREE_VIE_TPO;
                        prixPiedCarre = PRIX_TPO;
                        break;
                    case '3':
                        typeCouvertureToit = TYPE_COUVERTURE_EPDM;
                        dureeVieCouverture = DUREE_VIE_EPDM;
                        prixPiedCarre = PRIX_EPDM;
                        break;
                    case '4':
                        typeCouvertureToit = TYPE_COUVERTURE_GRAVIER_BITUME;
                        dureeVieCouverture = DUREE_VIE_GRAVIER_BITUME;
                        prixPiedCarre = PRIX_GRAVIER_BITUME;
                        break;
                    case '5':
                        typeCouvertureToit = TYPE_COUVERTURE_ASPHALTE;
                        dureeVieCouverture = DUREE_VIE_ASPHALTE;
                        prixPiedCarre = PRIX_ASPHALTE;
                        break;
                    case '6':
                        typeCouvertureToit = TYPE_COUVERTURE_TOLE;
                        dureeVieCouverture = DUREE_VIE_TOLE;
                        prixPiedCarre = PRIX_TOLE;
                        break;
                    default:
                        typeCouvertureToit = TYPE_COUVERTURE_ARDOISE;
                        dureeVieCouverture = DUREE_VIE_ARDOISE;
                        prixPiedCarre = PRIX_ARDOISE;
                        break;
                }

                // Saisie de la surface à couvrir en pieds carrés
                System.out.print("\nEntrez la surface à couvrir en pieds carrés : ");
                surfacePiedCarre = Clavier.lireDouble();

                while (surfacePiedCarre <= 0) {
                    System.out.println("\nEntrée invalide !");
                    System.out.print("\nEntrez la surface à couvrir en pieds carrés : ");
                    surfacePiedCarre = Clavier.lireDouble();
                }

                // Choix du mode de paiement
                System.out.print("\nEntrez le mode de paiement (s ou S pour Cash, d ou D pour Débit," +
                        " et c ou C pour Crédit) : ");
                choixModePaiement = Clavier.lireCharLn();

                while (choixModePaiement != 's' && choixModePaiement != 'S' && choixModePaiement != 'd'
                        && choixModePaiement != 'D'
                        && choixModePaiement != 'c' && choixModePaiement != 'C') {
                    System.out.println("\nEntrée invalide !");
                    System.out.print("\nEntrez le mode de paiement (s ou S pour Cash, d ou D pour Débit," +
                            " et c ou C pour Crédit) : ");
                    choixModePaiement = Clavier.lireCharLn();
                }

                if (choixModePaiement == 's' || choixModePaiement == 'S') {
                    modePaiement = PAIEMENT_CASH;
                } else if (choixModePaiement == 'd' || choixModePaiement == 'D') {
                    modePaiement = PAIEMENT_DEBIT;
                } else {
                    modePaiement = PAIEMENT_CREDIT;
                }

                // Calcul de la facture
                totalAvantTaxes = surfacePiedCarre * prixPiedCarre;
                montantTPS = totalAvantTaxes * TAUX_TPS;
                montantTVQ = totalAvantTaxes * TAUX_TVQ;
                totalFacture = totalAvantTaxes + montantTPS + montantTVQ;
                numeroFacture += 1;

                // Calcul du nombre et montant total des recouvrements pour sauvegarder dans le
                // 2ème choix du menu
                totalMontantRecouvrement = totalFacture + totalMontantRecouvrement;
                nombreRecouvrement = numeroFacture;

                // Affichage de la facture
                System.out.println(
                        "\n------------------------------------------------------------------------------------");
                System.out.println("    Toiture-Pour-Tous");
                System.out.println("    Facture no: " + numeroFacture + "                Date et Heure : "
                        + dateHeureSysteme.format(formatter));
                System.out.println(
                        "------------------------------------------------------------------------------------");
                System.out.printf("\n    Nom et prénom : %s %s            Téléphone : %s", nomClient, prenomClient,
                        telephoneClient);
                System.out.println("\n    Adresse du client : " + adresseClient);
                System.out.println("    Type de toit : " + typeToit);
                System.out.println("    Type de couverture : " + typeCouvertureToit);
                System.out.println("    La durée de vie : " + dureeVieCouverture);
                System.out.println("\n    La surface à couvrir : " + surfacePiedCarre + " pied carré.");
                System.out.println("    Le mode de paiement : " + modePaiement);
                System.out.printf("\n    Prix par pieds carré :              %.2f $", prixPiedCarre);
                System.out.printf("\n    Sous-total :                        %.2f $", totalAvantTaxes);
                System.out.printf("\n    Montant TPS :                       %.2f $", montantTPS);
                System.out.printf("\n    Montant TVQ :                       %.2f $", montantTVQ);
                System.out.printf("\n    Montant total :                     %.2f $", totalFacture);
                System.out.println("\n          ---------------------------------------------------         ");
                System.out.println("                        Merci pour votre confiance          ");
            }

            // Menu choix numéro 2 : Affichage du nombre et montant total des recouvrements
            else if (choixMenu == '2') {

                // Affichage du nombre et montant total des recouvrements
                System.out.println(
                        "\n------------------------------------------------------------------------------------");
                System.out.println("    Toiture-Pour-Tous");
                System.out.println("    Date et Heure : " + dateHeureSysteme.format(formatter));
                System.out.println(
                        "------------------------------------------------------------------------------------");
                System.out.println("\n");
                System.out.println(
                        "    Le nombre de recouvrements                                   " + nombreRecouvrement);
                System.out.printf("    Le montant total de toutes les installations                 %.2f $",
                        totalMontantRecouvrement);
                System.out.println("\n");
                System.out.println(
                        "\n------------------------------------------------------------------------------------");
            }

            // Menu choix numéro 3 : arrêt du programme
            else {
                choixMenu = '3';
            }
        } while (choixMenu == '1' || choixMenu == '2');

        // Message de remerciement et arrêt du programme
        System.out.print("\nMerci et à la prochaine !");
    } // main

} // InvoiceGenerator

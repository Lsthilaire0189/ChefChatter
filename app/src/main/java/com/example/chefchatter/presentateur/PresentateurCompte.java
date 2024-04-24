package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.activites.ConnexionCompteActivity;
import com.example.chefchatter.activites.CreationCompteActivity;
import com.example.chefchatter.activites.ModifierCompteActivity;
import com.example.chefchatter.dao.CompteCallBack;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;

public class PresentateurCompte {
    private Activity activity;

    private Modele modele;

    public PresentateurCompte(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }

    public void connexionCompte(Compte compte, CompteCallBack callBack){
        new Thread() {

            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    CompteMessage reponse = DAO.connexionCompte(compte);
                    callBack.onReponseRecieved(reponse);
                   // modele.setCompteCourrant(compte);
                  //  compteSetCallback.onCompteSet(reponse.getCompte());

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ((ConnexionCompteActivity) activity).afficherMessage(reponse);
                            ((ConnexionCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void creationCompte(Compte compte, CompteCallBack callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    CompteMessage reponse = DAO.creationCompte(compte);

                    callback.onReponseRecieved(reponse);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ((CreationCompteActivity) activity).afficherMessage(reponse);
                            ((CreationCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();

    }

    public void modifierCompte(Compte compte,CompteCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                   CompteMessage reponse= DAO.modifierCompte(compte);
                    callBack.onReponseRecieved(reponse);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ModifierCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void supprimerCompte(Compte compte) {
        new Thread() {
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    CompteMessage reponse= DAO.supprimerCompte(compte);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ModifierCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();

    }

    public void setCompte(Compte compte) {
       modele.setCompteCourrant(compte);
    }
    public Compte getCompte() {
       return modele.getCompteCourrant();
    }
}

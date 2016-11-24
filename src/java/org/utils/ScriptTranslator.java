/**
 * ********************************************************************
 * Class ScriptTranslator
 * Translation of string using the Microsoft API
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.utils;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScriptTranslator {

    /**
     * Language of the string
     */
    private String l1;

    /**
     * Language of the translation
     */
    private String l2;

    /**
     * String to translate
     */
    private String script;

    /**
     * Constructor
     *
     * @param l1 Language of the string
     * @param l2 Language of the translation
     * @param script String to translate
     */
    public ScriptTranslator(String l1, String l2, String script) {
        this.l1 = l1;
        this.l2 = l2;
        this.script = script;
    }

    /**
     * GETTERS AND SETTERS
     */
    /**
     *
     * @return Language of the String
     */
    public String getL1() {
        return l1;
    }

    /**
     *
     * @param l1 Language of the String
     */
    public void setL1(String l1) {
        this.l1 = l1;
    }

    /**
     *
     * @return String to translate
     */
    public String getScript() {
        return script;
    }

    /**
     *
     * @param script String to translate
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Translation of a script into english
     */
    public void translateScript() {

        Translate.setClientId("TISkype");
        Translate.setClientSecret("dJSB2Bz7VLJu0rugKpslUaNTRdVNHEy25Kty1qh1+gA=");

        try {
            if (l1.equalsIgnoreCase("fr")) {
                script = Translate.execute(script, Language.FRENCH, Language.ENGLISH);
            } else if (l1.equalsIgnoreCase("en")) {

            } else if (l1.equalsIgnoreCase("es")) {
                script = Translate.execute(script, Language.SPANISH, Language.ENGLISH);
            } else if (l1.equalsIgnoreCase("de")) {
                script = Translate.execute(script, Language.GERMAN, Language.ENGLISH);
            } else if (l1.equalsIgnoreCase("it")) {
                script = Translate.execute(script, Language.ITALIAN, Language.ENGLISH);
            } else if (l1.equalsIgnoreCase("cn")) {
                script = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
            } else {
                script = Translate.execute(script, Language.AUTO_DETECT, Language.ENGLISH);
            }

        } catch (Exception ex) {
            Logger.getLogger(ScriptTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Translation of a script into l2
     *
     * @return Translation
     */
    public String translate() {
        Translate.setClientId("TISkype");
        Translate.setClientSecret("dJSB2Bz7VLJu0rugKpslUaNTRdVNHEy25Kty1qh1+gA=");
        String tr = script;
        try {
            if (l1.equalsIgnoreCase("fr")) {

                if (l2.equalsIgnoreCase("fr")) {
                } else if (l2.equalsIgnoreCase("en")) {
                    tr = Translate.execute(script, Language.FRENCH, Language.ENGLISH);
                } else if (l2.equalsIgnoreCase("es")) {
                    tr = Translate.execute(script, Language.FRENCH, Language.SPANISH);
                } else if (l2.equalsIgnoreCase("de")) {
                    tr = Translate.execute(script, Language.FRENCH, Language.GERMAN);
                } else if (l2.equalsIgnoreCase("it")) {
                    tr = Translate.execute(script, Language.FRENCH, Language.ITALIAN);
                } else if (l2.equalsIgnoreCase("cn")) {
                    tr = Translate.execute(script, Language.FRENCH, Language.CHINESE_SIMPLIFIED);
                }

            } else if (l1.equalsIgnoreCase("en")) {

                if (l2.equalsIgnoreCase("fr")) {
                    tr = Translate.execute(script, Language.ENGLISH, Language.FRENCH);
                } else if (l2.equalsIgnoreCase("en")) {
                } else if (l2.equalsIgnoreCase("es")) {
                    tr = Translate.execute(script, Language.ENGLISH, Language.SPANISH);
                } else if (l2.equalsIgnoreCase("de")) {
                    tr = Translate.execute(script, Language.ENGLISH, Language.GERMAN);
                } else if (l2.equalsIgnoreCase("it")) {
                    tr = Translate.execute(script, Language.ENGLISH, Language.ITALIAN);
                } else if (l2.equalsIgnoreCase("cn")) {
                    tr = Translate.execute(script, Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
                }

            } else if (l1.equalsIgnoreCase("es")) {

                if (l2.equalsIgnoreCase("fr")) {
                    tr = Translate.execute(script, Language.SPANISH, Language.FRENCH);
                } else if (l2.equalsIgnoreCase("en")) {
                    tr = Translate.execute(script, Language.SPANISH, Language.ENGLISH);
                } else if (l2.equalsIgnoreCase("es")) {
                } else if (l2.equalsIgnoreCase("de")) {
                    tr = Translate.execute(script, Language.SPANISH, Language.GERMAN);
                } else if (l2.equalsIgnoreCase("it")) {
                    tr = Translate.execute(script, Language.SPANISH, Language.ITALIAN);
                } else if (l2.equalsIgnoreCase("cn")) {
                    tr = Translate.execute(script, Language.SPANISH, Language.CHINESE_SIMPLIFIED);
                }

            } else if (l1.equalsIgnoreCase("de")) {

                if (l2.equalsIgnoreCase("fr")) {
                    tr = Translate.execute(script, Language.GERMAN, Language.FRENCH);
                } else if (l2.equalsIgnoreCase("en")) {
                    tr = Translate.execute(script, Language.GERMAN, Language.ENGLISH);
                } else if (l2.equalsIgnoreCase("es")) {
                    tr = Translate.execute(script, Language.GERMAN, Language.SPANISH);
                } else if (l2.equalsIgnoreCase("de")) {
                } else if (l2.equalsIgnoreCase("it")) {
                    tr = Translate.execute(script, Language.GERMAN, Language.ITALIAN);
                } else if (l2.equalsIgnoreCase("cn")) {
                    tr = Translate.execute(script, Language.GERMAN, Language.CHINESE_SIMPLIFIED);
                }

            } else if (l1.equalsIgnoreCase("it")) {

                if (l2.equalsIgnoreCase("fr")) {
                    tr = Translate.execute(script, Language.ITALIAN, Language.FRENCH);
                } else if (l2.equalsIgnoreCase("en")) {
                    tr = Translate.execute(script, Language.ITALIAN, Language.ENGLISH);
                } else if (l2.equalsIgnoreCase("es")) {
                    tr = Translate.execute(script, Language.ITALIAN, Language.SPANISH);
                } else if (l2.equalsIgnoreCase("de")) {
                    tr = Translate.execute(script, Language.ITALIAN, Language.GERMAN);
                } else if (l2.equalsIgnoreCase("it")) {
                } else if (l2.equalsIgnoreCase("cn")) {
                    tr = Translate.execute(script, Language.ITALIAN, Language.CHINESE_SIMPLIFIED);
                }

            } else if (l1.equalsIgnoreCase("cn")) {

                if (l2.equalsIgnoreCase("fr")) {
                    tr = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.FRENCH);
                } else if (l2.equalsIgnoreCase("en")) {
                    tr = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
                } else if (l2.equalsIgnoreCase("es")) {
                    tr = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.SPANISH);
                } else if (l2.equalsIgnoreCase("de")) {
                    tr = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.GERMAN);
                } else if (l2.equalsIgnoreCase("it")) {
                    tr = Translate.execute(script, Language.CHINESE_SIMPLIFIED, Language.ITALIAN);
                } else if (l2.equalsIgnoreCase("cn")) {
                }

            } else if (l2.equalsIgnoreCase("fr")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.FRENCH);
            } else if (l2.equalsIgnoreCase("en")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.ENGLISH);
            } else if (l2.equalsIgnoreCase("es")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.SPANISH);
            } else if (l2.equalsIgnoreCase("de")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.GERMAN);
            } else if (l2.equalsIgnoreCase("it")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.ITALIAN);
            } else if (l2.equalsIgnoreCase("cn")) {
                tr = Translate.execute(script, Language.AUTO_DETECT, Language.CHINESE_SIMPLIFIED);
            }

        } catch (Exception ex) {
            Logger.getLogger(ScriptTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tr;
    }
}

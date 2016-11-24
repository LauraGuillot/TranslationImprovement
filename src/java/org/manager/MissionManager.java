/**
 * ********************************************************************
 * Interface MissionManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import org.tables.Hasmission;

public interface MissionManager {

    public int genereId(int playerID);

    public void newMission(int playerID);

    public String newBody(String s, double n);

    public boolean success(String s);

    public void updateMission(Hasmission m);

    public void update1(Hasmission m);

    public void update2(Hasmission m);

    public void update3(Hasmission m);

    public void update4(Hasmission m);

    public void update5(Hasmission m);

    public void update6(Hasmission m);

    public void update7(Hasmission m);

    public void update8(Hasmission m);

    public void update9(Hasmission m);

    public void update10(Hasmission m);

    public void update11(Hasmission m);

    public ArrayList<String> updateMissionGlobal(int id, int eval, int sub, int pts, boolean challenge20);

    public void incrPtsMission(Hasmission m, int pts);

    public void updateMissionEval(Hasmission m, int n);

    public void updateMissionSub(Hasmission m, int n);

    public void challenge20(Hasmission m);

    public ArrayList<Hasmission> getMyMissions(int id);

    public ArrayList<String> getMissionTxt(int id);

    public void get3New(int id);
}

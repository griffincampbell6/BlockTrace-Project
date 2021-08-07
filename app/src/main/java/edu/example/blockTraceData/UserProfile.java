package edu.example.blockTraceData;

import android.view.animation.AccelerateInterpolator;

//this class Holds the local data for the user during application life cycle
public class UserProfile
{
    // this is the profile of active user right now;
    private static UserProfile ActiveProfile;
    public Person profileOwner;
    public Person[] userContacts;

    // we should only need to call this method once in the  applicaton life cycle;
    // unless we have some sort of login logout feature
    // then we would have to call this method again for the new person that could've logged in
    public static void SetActiveProfile(Person newPerson)
    {
        Init();
        ActiveProfile.profileOwner = newPerson;
    }
    public static void SetContactsList(Person[] contacts)
    {
        Init();
        ActiveProfile.userContacts = contacts;
    }
    public static UserProfile GetActiveLocalProfile()
    {
        Init();
        return ActiveProfile;

    }
    private static void Init()
    {
        if(ActiveProfile==null)
            ActiveProfile = new UserProfile();
    }
}

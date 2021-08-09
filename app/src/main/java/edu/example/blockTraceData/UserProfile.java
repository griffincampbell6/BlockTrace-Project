package edu.example.blockTraceData;

import android.util.Log;
import android.view.animation.AccelerateInterpolator;

import org.json.JSONException;

import java.util.function.BiConsumer;

//this class Holds the local data for the user during application life cycle
public class UserProfile
{
    // this is the profile of active user right now;
    private static UserProfile ActiveProfile;
    public Person profileOwner;
    public Person[] userContacts = new Person[]{};

    // we should only need to call this method once in the  applicaton life cycle;
    // unless we have some sort of login logout feature
    // then we would have to call this method again for the new person that could've logged in
    public static void SetActivePerson(Person newPerson)
    {
        Init();
        ActiveProfile.profileOwner = newPerson;
    }
    public static void SetContactsList(Person[] contacts)
    {
        Init();
        ActiveProfile.userContacts = contacts;
    }
    public static UserProfile GetActivePofile()
    {
        Init();
        return ActiveProfile;

    }
    public static void RefreshContacts(BiConsumer<ResponseStatus,Person[]> callback)
    {
        try
        {
            RequestController.GetAllContacts(UserProfile.GetActivePofile().profileOwner, (s,p)->
            {
                OnContactsRecieved(s,p);
                if(callback!=null)
                callback.accept(s,p);
            });
        }
        catch (JSONException ex)
        {

        }

    }
    static  void OnContactsRecieved(ResponseStatus status, Person[] allContacts)
    {
        if(status.isValid)
        {
            int len = allContacts.length;
            for(int i=0; i<len; ++i)
                Log.v("API",  allContacts[i].userName);
            SetContactsList(allContacts);
        }
        else
            Log.v("API",  status.errorMessage);
    }
    private static void Init()
    {
        if(ActiveProfile==null)
            ActiveProfile = new UserProfile();
    }
}

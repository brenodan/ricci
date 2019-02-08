# Remote Inter-Component Communication -- *RICCi* reproducibility repository

This repository contains the RICCi code generator, code samples, energy readings, and collected metrics from the samples projects built both using RICCi and hand-written for the data exchange patterns copy, remote, and stream. 
    
 *- RICCi demos are based on the PigeonMessanger project available at  https://github.com/deib-polimi/PigeonMessanger.
 *- In order to run the demos, you will need to use two Android devices with WiFi capabilities, as the demos use WiFi direct to communicate.

### Installing

* Download library/aar/ricci.aar
* Add arr file to app's dependencies (https://stackoverflow.com/questions/29826717/how-to-import-a-aar-file-into-android-studio-1-1-0-and-use-it-in-my-code/38749847)
* Register RICCi's service in the manifest file 
```
<service android:name="com.example.riccilib.ricci.services.BasicIntentService" />
```
* Register RICCi's broadcast receiver
```
registerReceiver(new BasicIntentBroadcastReceiver(), filter);
```
* Define how the RemoteIntent will be handled by the target application by calling one of the pre-implemented functions for either *stream*, *copy*, or *remote* and forward the generated Intent object to the RICCi service.

```
Intent intent = handleCopyIntent(data);
startService(intent);

```
* With the previous steps in place and with two devices connected you should be able to send a *RemoteIntent*
```
RemoteIntent remoteIntent = new RemoteIntent(Intent.ACTION_PICK, Transfer.COPY);
remoteIntent.setData(ContactsContract.Contacts.CONTENT_URI);
remoteIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
```

## Authors

* **Breno Dantas Cruz** - *Published work* 
* - [Programming Support for Data Intensive Distributed Mobile Applications at the Edge](http://people.cs.vt.edu/~bdantasc/papers/mobilesoftsrc18.pdf) 
* - [Intent to Share: Enhancing Android Inter-Component Communication for Distributed Devices] (http://people.cs.vt.edu/~tilevich/papers/ricci-mobilesoft18.pdf)

## Acknowledgments

* *PigeonMessanger* (https://github.com/deib-polimi/PigeonMessanger)
* *LipeRMI* (http://lipermi.sourceforge.net/)


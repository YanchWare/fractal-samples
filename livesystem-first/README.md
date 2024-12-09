# LiveSystem-First Samples

* Focus on the end system for quick roll-outs
* Best for PoCs and Moonshots projects

The approach used here is LiveSystem-First so that we can use the powerful SDK constructs in order to define infrastructure through classic IaC approach. 
This is a good enough approach for quick prototyping and MVPs. However, we advise to use the Fractal-First approach as soon as possible, as that will allow delegating more control 
to the development teams without hindering governance and security.

## Code organization

The repository is split into different directories based on the cloud provider. See the list below for easier navigation.

| Cloud Provider        	 | Description                                             |
|-------------------------|---------------------------------------------------------|
| [ AWS ](./aws/) 	       | Collection of sample projects using our SDK for AWS 	   |
| [ Azure ](./azure/) 	   | Collection of sample projects using our SDK for Azure 	 |
| [ GCP   ](./gcp/) 	     | Collection of sample projects using our SDK for GCP   	 |
| [ OCI   ](./oci/) 	     | Collection of sample projects using our SDK for OCI   	 |

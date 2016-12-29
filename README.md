# ambari-vagrant-cluster

# Goal 
  To detail the process of setting up a multi-node cluster using Apache Ambari and Vagrant on Windows. This allows you to set up your     own multi-node cluster on your personal computer using virtual machines.

# Infrastructure used
  Windows 8, 16GB RAM, 500GB HDD; A mimimum of 16GB RAM is recommended for a 3-node cluster.

# Step-1 : Install the VMs
1.Install VirtualBox from: https://www.virtualbox.org/wiki/Downloads : Needed for VMs to run.
2.Install Vagrant from: http://downloads.vagrantup.com : Needed for installing VMs.
3.After you have installed VirtualBox and Vagrant on your computer, check out the “ambari-vagrant” repo on github:
  git clone https://github.com/u39kun/ambari-vagrant.git
4.Open command prompt and type "vagrant". It creates a private key as C:\Users\$user_name\.vagrant.d\insecure_private_key. This key will   be used in the following steps.
5.Navigate to folder path of the repo downloaded from github:
  path-to-ambari-vagrant-master\ambari-vagrant-master\centos7.0(CentOS is preferred as it is quicker than other OS to launch).Copy the     insecure_private_key from C:\Users\$user_name\.vagrant.d\insecure_private_key to the current location.
6.Open the current location in command prompt and type "vagrant up". Installation process of virtual machines begins.
7.By default, the process installs 10 VMs. Optimal number of nodes for 16GB RAM machine is 3 nodes. 
# Note
  Note down the ports in which the VMs are created.
  So after completing the installation for 3 nodes, the process can be stopped(CTRL+C). A folder named .vagrant gets created under         path-to-ambari-vagrant-master\ambari-vagrant-master\centos7.0 with all the virtual machines listed.
8.Login into putty with the following credentials,
  IP : 127.0.0.1, Port : (as seen in the command prompt for VMs), Username : vagrant, Password : vagrant
  
  ##### This is the first success #####

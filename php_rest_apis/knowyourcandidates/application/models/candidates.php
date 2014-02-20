<?php
class Candidates extends CI_Model {

	public function __construct(){
		$this->load->database();
	}
	
	public function get_candidates(){
		$query = $this->db->get('knowyourcandidates.MP2014');
		return $query->result_array();
	}
	/**
	* This method parses the json from a file and persists to a DB table
	Example:
	{
		"placeofbirth": "Chennai (Tamil Nadu)",
		"socialandculturalactivities": "Working for the upliftment of the backward, dalits and minorities",
		"sportsandclubs": "Football and kabbadi",
		"mothername": "Smt. S.J.M.H. Bivi",
		"partyname": "Indian National Congress(INC)",
		"favoritepasttimeandrecreation": "Public speaking",
		"specialinterests": "Promoting communal harmony in remote dalit villages",
		"otherinformation": ", Rubber Board, Ministry of Commerce and Industry",
		"emailid": "jm.aaronrasheed@sansad.nic.in",
		"presentaddress": "\n3, Mahadev Road,\nNew Delhi - 110 001\nTel. (011) 23355101, Fax. (011) 23355102 9868180133 (M)\n\n\n\n",
		"candidateid": "4064",
		"imageurl": "http://164.100.47.132/mpimage/photo/4064.jpg",
		"permanentaddress": "\n(i) 7, VRC Road, Street - I\nTeynampet, Chennai - 600 002 Tamil Nadu\nTels.(044) 28156283, 09444048611(M)\n(ii) 126-Cumbum Road, P.C. Patti, Theni- 625 531 Tamil Nadu Tel. (04546) 265699\n\n\n",
		"spousename": "Smt. J.M.H.Hazaran Bi",
		"noofdaughters": "2",
		"marriagedate": "29 Nov 1978",
		"profession": "Businessperson Agriculturist",
		"constituency": "Theni (Tamil Nadu )",
		"dateofbirth": "13 May 1950",
		"noofsons": "2",
		"education": "Intermediate Educated at Sir Thiyagaraja College, Washermanpet, Chennai, Tamil Nadu",
		"name": "Aaroon Rasheed,Shri J.M.",
		"maritalstatus": "Married",
		"countriesvisited": "Widely travelled",
		"positionsheld": "\n1996-2001\nMember, Tamil Nadu Legislative Assembly\n\n2004\nElected to 14th Lok Sabha\nMember, Committee on Energy\nMember, Consultative Committee, Ministry of Commerce and Industry\nMember, Consultative Committee, Ministry of Shipping\nMember, Committee on Government Assurances\n\n2 Jan 2006\nMember, Joint Parliamentary Committee on Wakf\n\n5 Aug 2007\nMember, Committee on Energy\n\n2009\nRe-elected to 15th Lok Sabha (2nd term)\n\n31 Aug 2009\nMember, Committee on Health and Family Welfare\nMember, Committee on Official Languages\nMember, Consultative Committee, Ministry of Shipping\n",
		"fathername": "Shri Jamal Mohideen"
	}
	*/
	public function persist_candidates(){
		$MP_JSONS_DIR = 'C:\Users\vasanth\Documents\GitHub\14feb2014\opengovtdata\opengovtdata\jsoupscraper\mpfiles';

		if ($handle = opendir($MP_JSONS_DIR)) {
			while (false !== ($mpjsonfilename = readdir($handle))) {
				$ext = pathinfo($mpjsonfilename, PATHINFO_EXTENSION);
				if ($mpjsonfilename != "." && $mpjsonfilename != ".." && $ext == "json") {
					$file = file_get_contents($MP_JSONS_DIR."/".$mpjsonfilename, FILE_USE_INCLUDE_PATH);
					$json_arry = json_decode($file,true);
					
					//TODO: To replace this with batch insert 
					foreach ($json_arry as $json_arr){
						$this->db->insert('knowyourcandidates.loksabha_15_531', $json_arr); 
					}
					
					//TODO: The following line doesn't work and it gives this error
					/**
					* A Database Error Occurred
					* Error Number: 1064
					* You have an error in your SQL syntax; check the manual that corresponds to your MySQL 
					* server version for the right syntax to use near 'Array' at line 1
					*/
					//$this->db->insert_batch('knowyourcandidates.loksabha_15_531',$json_arry); 
				}
			}
			closedir($handle);
		}
	}
}
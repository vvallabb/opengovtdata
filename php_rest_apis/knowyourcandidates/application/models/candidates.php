<?php
class Candidates extends CI_Model {

	public function __construct(){
		$this->load->database();
	}
	
	public function get_candidates(){
		$query = $this->db->get('knowyourcandidates.MP2014');
		return $query->result_array();
	}
}
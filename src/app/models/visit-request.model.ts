import { User } from "./user.model";
import { Visitor } from "./visitor.model";

export interface VisitRequest {
  idVisitRequest?: number;
  visitDate: string; // ISO format date: "YYYY-MM-DD"
  expectedArrival: string;
  expectedDeparture: string;
  visitPurpose?: string;
  visitRequestStatus: VisitRequestStatus;
  requester: User;
  visitor?: Visitor;
  personneConcernee?: string;
}

export enum VisitRequestStatus {
   'PENDING',
   'APPROVED',
   'REJECTED',
   'CANCELLED',
   'COMPLETED'}

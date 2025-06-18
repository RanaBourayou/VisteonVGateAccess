export interface Visitor {
     idVisitor?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: number;
  companyName?: string;
  cin?: number;
  passportNumber?: number;
  visitorType?: VisitorType;
}
export enum VisitorType {
 'SUPPLIER',
    'INTERN',
    'CANDIDATE',
    'GUEST'
}
